package com.kaliente.pos.application.services;

import com.kaliente.pos.application.models.dtos.order.OrderCustomerDto;
import com.kaliente.pos.application.models.dtos.order.OrderProductDto;
import com.kaliente.pos.application.requests.order.CancelOrderRequest;
import com.kaliente.pos.application.requests.order.CompleteOrderRequest;
import com.kaliente.pos.application.requests.order.CreateOrderRequest;
import com.kaliente.pos.application.requests.order.MakePaymentForOrderRequest;
import com.kaliente.pos.domain.orderaggregate.*;
import org.aspectj.weaver.ast.Or;
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
    private ModelMapper modelMapper;


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
    public Order createOrder(CreateOrderRequest request) {

        Set<OrderProduct> orderedProducts = request.getOrderedProducts()
                .stream().map(x -> modelMapper.map(x, OrderProduct.class)).collect(Collectors.toSet());
        OrderCustomer orderedBy = modelMapper.map(request.getOrderedBy(), OrderCustomer.class);

        Order orderToCreate = Order.builder()
                .status(OrderStatus.TAKEN)
                .orderingDate(new Date())
                .orderedBy(orderedBy)
                .orderProducts(orderedProducts)
                .build();

        orderToCreate.getOrderProducts().forEach(op -> op.setBelongingOrder(orderToCreate));

        orderToCreate.setOrderedBy(orderedBy);

        return orderRepository.save(orderToCreate);
    }

    public Order cancelOrder(CancelOrderRequest request) {
        Optional<Order> orderToCancel = orderRepository.findById(request.getOrderId());
        if(orderToCancel.isPresent()) {
            orderToCancel.get().setStatus(OrderStatus.CANCELLED);
            return orderToCancel.get();
        }
        return null;
    }

    public Order completeOrder(CompleteOrderRequest request) {
        Optional<Order> orderToComplete = orderRepository.findById(request.getOrderId());
        if(orderToComplete.isPresent()) {
            orderToComplete.get().setStatus(OrderStatus.COMPLETED);
            return orderToComplete.get();
        }
        return null;
    }

    public Order makePaymentForOrder(MakePaymentForOrderRequest request) {
        Optional<Order> order = orderRepository.findById(request.getOrderId());

        if(order.isPresent()) {
            OrderTransaction newTransaction = OrderTransaction.builder()
                    .belongingOrder(order.get())
                    // TODO: Add currency for OrderTransaction builder.
                    .paymentMethod(request.getPaymentMethod())
                    .paidAmount(request.getAmount())
                    .build();


//            order.get().getPaymentTransactions().add(newTransaction);

            return order.get();
        }

        return null;
    }



}
