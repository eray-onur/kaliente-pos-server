package com.kaliente.pos.application.services;

import com.kaliente.pos.api.configs.AppConfig;
import com.kaliente.pos.application.models.CurrencyDto;
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

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CurrencyHistoryService currencyHistoryService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    AppConfig appConfig;


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

        ArrayList<CurrencyDto> currencies = currencyHistoryService.getAllCurrencies();


        Set<OrderProduct> orderedProducts = request.getOrderedProducts()
                .stream().map(x -> modelMapper.map(x, OrderProduct.class)).collect(Collectors.toSet());

        for(var orderProduct : request.getOrderedProducts()) {
            Optional<CurrencyDto> foundCurrency = currencies
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


        Optional<CurrencyDto> orderCurrency = currencies
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

    public Order createFullOrder(CreateFullOrderRequest request) {
        ArrayList<CurrencyDto> currencies = currencyHistoryService.getAllCurrencies();


        Set<OrderProduct> orderedProducts = request.getOrderedProducts()
                .stream().map(x -> modelMapper.map(x, OrderProduct.class)).collect(Collectors.toSet());

        for(var orderProduct : request.getOrderedProducts()) {
            Optional<CurrencyDto> foundCurrency = currencies
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

        Optional<CurrencyDto> orderCurrency = currencies
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
            Optional<CurrencyDto> foundCurrency = currencies
                    .stream()
                    .filter(c -> Objects.equals(
                            c.getCurrencyTitle(),
                            transaction.getPaymentCurrencyTitle())
                    )
                    .findFirst();

            if(foundCurrency.isEmpty())
                throw new IllegalStateException("One transaction's currency title is not valid.");

            OrderTransaction transactionToAdd = modelMapper.map(transaction, OrderTransaction.class);
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

        //

        Order orderToCreate = Order.builder()
                .currency(currency)
                .status(OrderStatus.TAKEN)
                .orderingDate(new Date())
                .orderedBy(orderedBy)
                .orderProducts(orderedProducts)
                .paymentTransactions(transactions)
                .build();

        // Total price calculation
        double totalPrice = 0.0;
        for(var product : orderToCreate.getOrderProducts()) {
            if(product.getCurrency().getCurrencyTitle().equals(appConfig.getMainCurrencyTitle())) {
                totalPrice += product.getPrice();
            }
            else {
                totalPrice += (product.getPrice() * product.getCurrency().getCurrencyRate());
            }
        }
        //
        // Transaction calculation
        double transactionTotalAmount = 0.0;
        for(var transaction : orderToCreate.getPaymentTransactions()) {
            if(transaction.getTransactionCurrency().getCurrencyTitle().equals(appConfig.getMainCurrencyTitle())) {
                transactionTotalAmount += transaction.getPaidAmount();
            }
            else {
                transactionTotalAmount += (transaction.getPaidAmount() * transaction.getTransactionCurrency().getCurrencyRate());
            }
        }
        if(transactionTotalAmount > totalPrice) {
            throw new UnsupportedOperationException("Transaction failed to be added, reason: exceeds total price.");
        }
        //

        orderToCreate.getOrderProducts().forEach(op -> op.setBelongingOrder(orderToCreate));
        orderToCreate.getPaymentTransactions().forEach(pt -> pt.setBelongingOrder(orderToCreate));
        orderToCreate.setOrderedBy(orderedBy);

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

    public Order createTransaction(CreateTransactionForOrderRequest request) {

        Optional<Order> order = orderRepository.findById(request.getOrderId());

        if(order.isEmpty()) {
            throw new IllegalStateException("Failed to find the order for transaction.");
        }

        CurrencyDto foundCurrency = currencyHistoryService.getLatestRateByTitle(request.getPaymentCurrencyTitle());

        if(foundCurrency == null) {
            throw new IllegalStateException("Given currency is not supported (" + request.getPaymentCurrencyTitle() + ").");
        }

        OrderCurrency orderCurrency = new OrderCurrency(foundCurrency.getCurrencyTitle(), foundCurrency.getBaseCrossRate(), foundCurrency.getCurrencyDate(), foundCurrency.getCurrencyRate());

        OrderTransaction newTransaction = OrderTransaction.builder()
                    .paymentMethod(request.getPaymentMethod())
                    .transactionCurrency(orderCurrency)
                    .paidAmount(request.getPaidAmount())
                    .build();

        newTransaction.setBelongingOrder(order.get());
        order.get().getPaymentTransactions().add(newTransaction);

        // Payment Calculation
        double totalPrice = order.get().getTotalPrice();
        double newTotalPrice = 0.0;

        for(OrderTransaction transaction : order.get().getPaymentTransactions()) {
            if(transaction.getTransactionCurrency().getCurrencyTitle().equals(appConfig.getMainCurrencyTitle())) {
                newTotalPrice += transaction.getPaidAmount();
            }
            else {
                newTotalPrice += (transaction.getPaidAmount() * transaction.getTransactionCurrency().getCurrencyRate());
            }
        }
        // TODO: Create Backpayment transaction if new total exceeds total price.
        if(newTotalPrice > totalPrice) {
            throw new UnsupportedOperationException("Transaction failed to be added, reason: exceeds total price.");
        }
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
