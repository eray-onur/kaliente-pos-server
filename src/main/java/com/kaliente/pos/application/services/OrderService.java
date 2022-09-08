package com.kaliente.pos.application.services;

import com.kaliente.pos.application.configs.AppConfig;
import com.kaliente.pos.application.models.CurrencyModel;
import com.kaliente.pos.application.requests.order.*;
import com.kaliente.pos.domain.orderaggregate.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CurrencyHistoryService currencyHistoryService;
    private final ModelMapper modelMapper;

    private final AppConfig appConfig;

    @Autowired
    public OrderService(OrderRepository orderRepository, CurrencyHistoryService currencyHistoryService, ModelMapper modelMapper, AppConfig appConfig) {
        this.orderRepository = orderRepository;
        this.currencyHistoryService = currencyHistoryService;
        this.modelMapper = modelMapper;
        this.appConfig = appConfig;
    }


    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getOrderById(UUID orderId) {
        Optional<Order> foundOrder = orderRepository.findById(orderId);
        return foundOrder.orElse(null);
    }

    public Order getOrderByCustomerId(UUID customerId) {
        return orderRepository.findByOrderedBy_Id(customerId);
    }

    @Transactional
    public Order createPartialOrder(CreatePartialOrderRequest request) {

        ArrayList<CurrencyModel> currencies = currencyHistoryService.getAllCurrencies();


        Set<OrderProduct> orderedProducts = request.getOrderedProducts()
                .stream().map(x -> modelMapper.map(x, OrderProduct.class)).collect(Collectors.toSet());

        for(var orderProduct : request.getOrderedProducts()) {
            Optional<CurrencyModel> foundCurrency = currencies
                    .stream()
                    .filter(c -> Objects.equals(c.getCurrencyTitle(), orderProduct.getCurrencyTitle()))
                    .findFirst();

            if(foundCurrency.isEmpty()) {
                throw new IllegalStateException(
                        "Could not find a valid currency for ordered product "
                        + orderProduct.getOrderedProductTitle()
                        + ".");
            }

            var orderProductCurrency = modelMapper.map(foundCurrency.get(), OrderCurrency.class);

            orderedProducts.stream()
                    .filter(op -> op.getOrderedProductId() == orderProduct.getOrderedProductId()).findFirst()
                    .get()
                    .setCurrency(orderProductCurrency);
        }


        Optional<CurrencyModel> orderCurrency = currencies
                .stream()
                .filter(c -> Objects.equals(c.getCurrencyTitle(), request.getCurrencyTitle()))
                .findFirst();

        if(orderCurrency.isEmpty()) {
            throw new IllegalStateException("Main currency state of order could not be initialized.");
        }

        var currency = new OrderCurrency(
                orderCurrency.get().getCurrencyTitle(),
                orderCurrency.get().getBaseCrossRate(),
                orderCurrency.get().getCurrencyDate(),
                orderCurrency.get().getCurrencyRate()
        );


        OrderCustomer orderedBy = modelMapper.map(request.getOrderedBy(), OrderCustomer.class);

        Order orderToCreate = Order.builder()
                .currency(currency)
                .status(OrderStatus.AWAITING_PAYMENT)
                .orderingDate(new Date())
                .orderedBy(orderedBy)
                .orderProducts(orderedProducts)
                .build();

        orderToCreate.getOrderProducts().forEach(op -> op.setBelongingOrder(orderToCreate));
        orderToCreate.setOrderedBy(orderedBy);

        return orderRepository.save(orderToCreate);
    }

    @Transactional
    public Order createFullOrder(CreateFullOrderRequest request) {
        ArrayList<CurrencyModel> currencies = currencyHistoryService.getAllCurrencies();


        Set<OrderProduct> orderedProducts = request.getOrderedProducts()
                .stream().map(x -> modelMapper.map(x, OrderProduct.class)).collect(Collectors.toSet());

        for(var orderProduct : request.getOrderedProducts()) {
            Optional<CurrencyModel> foundCurrency = currencies
                    .stream()
                    .filter(c -> Objects.equals(c.getCurrencyTitle(), orderProduct.getCurrencyTitle()))
                    .findFirst();

            if(foundCurrency.isEmpty()) {
                throw new IllegalStateException(
                        "Could not find a valid currency for ordered product "
                                + orderProduct.getOrderedProductTitle()
                                + ".");
            }

            var orderProductCurrency = new OrderCurrency(
                    foundCurrency.get().getCurrencyTitle(),
                    foundCurrency.get().getBaseCrossRate(),
                    foundCurrency.get().getCurrencyDate(),
                    foundCurrency.get().getCurrencyRate()
            );

            orderedProducts.stream()
                    .filter(op -> op.getOrderedProductId() == orderProduct.getOrderedProductId()).findFirst()
                    .get()
                    .setCurrency(orderProductCurrency);
        }

        Optional<CurrencyModel> orderCurrency = currencies
                .stream()
                .filter(c -> Objects.equals(c.getCurrencyTitle(), request.getCurrencyTitle()))
                .findFirst();

        if(orderCurrency.isEmpty()) {
            throw new IllegalStateException("Main currency state of order could not be initialized.");
        }

        var currency = new OrderCurrency(
                orderCurrency.get().getCurrencyTitle(),
                orderCurrency.get().getBaseCrossRate(),
                orderCurrency.get().getCurrencyDate(),
                orderCurrency.get().getCurrencyRate()
        );

        // Customer mapping
        OrderCustomer orderedBy = modelMapper.map(request.getOrderedBy(), OrderCustomer.class);
        //
        // Transaction mapping
        Set<OrderTransaction> transactions = new HashSet<>();

        for(var transaction : request.getTransactions()) {
            Optional<CurrencyModel> foundCurrency = currencies
                    .stream()
                    .filter(c -> Objects.equals(
                            c.getCurrencyTitle(),
                            transaction.getPaymentCurrencyTitle())
                    )
                    .findFirst();

            if(foundCurrency.isEmpty())
                throw new IllegalStateException("One transaction's currency title is not valid.");

            OrderTransaction transactionToAdd = modelMapper.map(transaction, OrderTransaction.class);
            transactionToAdd.setPaidAmount(transaction.getPaidAmount());
            transactionToAdd.setTransactionType(TransactionType.PAYMENT);
            transactionToAdd.setTransactionCurrency(
                    new OrderCurrency(
                            foundCurrency.get().getCurrencyTitle(),
                            foundCurrency.get().getBaseCrossRate(),
                            foundCurrency.get().getCurrencyDate(),
                            foundCurrency.get().getCurrencyRate()
                    )
            );

            transactions.add(transactionToAdd);

        }

        Order orderToCreate = Order.builder()
                .currency(currency)
                .status(OrderStatus.TAKEN)
                .orderingDate(new Date())
                .orderedBy(orderedBy)
                .orderProducts(orderedProducts)
                .paymentTransactions(transactions)
                .build();

        // Total price calculation

        // Total price of all the products.
        double totalProductPrice = orderToCreate.getOrderProducts().stream().mapToDouble(
                op -> (op.getCurrency().getCurrencyRate() == 1 // Checking if the given currency object is the main currency.
                        ? op.getPrice()
                        : op.getPrice() * op.getCurrency().getCurrencyRate()
                        * op.getOrderedProductQuantity()
                )
        ).sum();

        // Total cost for the order giver.
        double paymentPrice = orderToCreate.getPaymentTransactions().stream()
                .filter(pt -> pt.getTransactionType().equals(TransactionType.PAYMENT))
                .mapToDouble(
                        op -> (op.getTransactionCurrency().getCurrencyRate() == 1 // Checking if the given currency object is the main currency.
                                ? op.getPaidAmount()
                                : op.getPaidAmount() * op.getTransactionCurrency().getCurrencyRate())
                ).sum();
        //

        if(paymentPrice < totalProductPrice) {
            throw new RuntimeException("Cannot create a full order. Insufficient payment.");
        }


        orderToCreate.getOrderProducts().forEach(op -> op.setBelongingOrder(orderToCreate));
        orderToCreate.getPaymentTransactions().forEach(pt -> pt.setBelongingOrder(orderToCreate));
        orderToCreate.setOrderedBy(orderedBy);
        orderToCreate.setCompletionDate(new Date());
        orderToCreate.setStatus(OrderStatus.COMPLETED);

        return orderRepository.save(orderToCreate);

    }

    public Order cancelOrder(CancelOrderRequest request) {
        Optional<Order> orderToCancel = orderRepository.findById(request.getOrderId());
        if(orderToCancel.isEmpty()) {
            return null;
        }
        orderToCancel.get().setStatus(OrderStatus.CANCELLED);
        return orderRepository.save(orderToCancel.get());
    }

    public Order completeOrder(CompleteOrderRequest request) {
        Optional<Order> orderToComplete = orderRepository.findById(request.getOrderId());
        if(orderToComplete.isEmpty()) {
            return null;
        }
        orderToComplete.get().setStatus(OrderStatus.COMPLETED);
        return orderRepository.save(orderToComplete.get());
    }

    @Transactional
    public Order createTransaction(CreateTransactionForOrderRequest request) {

        Optional<Order> order = orderRepository.findById(request.getOrderId());

        if(order.isEmpty()) {
            throw new IllegalStateException("Failed to find the order for transaction.");
        }

        CurrencyModel foundCurrency = currencyHistoryService.getLatestRateByTitle(request.getPaymentCurrencyTitle());

        if(foundCurrency == null) {
            throw new IllegalStateException("Given currency is not supported (" + request.getPaymentCurrencyTitle() + ").");
        }

        OrderCurrency orderCurrency = new OrderCurrency(foundCurrency.getCurrencyTitle(), foundCurrency.getBaseCrossRate(), foundCurrency.getCurrencyDate(), foundCurrency.getCurrencyRate());

        OrderTransaction newTransaction = OrderTransaction.builder()
                    .transactionMethod(request.getPaymentMethod())
                    .transactionCurrency(orderCurrency)
                    .paidAmount(request.getPaidAmount())
                    .build();

        newTransaction.setBelongingOrder(order.get());

        HashSet<OrderTransaction> transactionContainer = new HashSet<>();
        transactionContainer.add(newTransaction);

        // Total price calculation
        order.get().addNewTransactions(transactionContainer);
        //
        orderRepository.save(order.get());
        return order.get();
    }

    public Order updateCustomerInformation(UpdateCustomerInformationRequest request) {

        Optional<Order> order = orderRepository.findById(request.getOrderId());

        if(order.isEmpty()) {
            return null;
        }

        OrderCustomer customerToUpdate = order.get().getOrderedBy();
        customerToUpdate.setEmail(request.getEmail());
        customerToUpdate.setFirstName(request.getFirstName());
        customerToUpdate.setLastName(request.getLastName());
        customerToUpdate.setCustomerType(request.getCustomerType());

        return orderRepository.save(order.get());

    }


}
