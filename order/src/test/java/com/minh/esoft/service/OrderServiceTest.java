package com.minh.esoft.service;

import com.minh.esoft.auth.JwtUserDetail;
import com.minh.esoft.common.enums.AccountStatusEnum;
import com.minh.esoft.common.enums.OrderCategoryEnum;
import com.minh.esoft.common.enums.OrderServiceEnum;
import com.minh.esoft.common.enums.OrderStatusEnum;
import com.minh.esoft.common.exception.DataNotFoundException;
import com.minh.esoft.common.exception.DataNotRelevantToUserException;
import com.minh.esoft.common.exception.DataNotUpdateException;
import com.minh.esoft.repository.OrdersRepository;
import com.minh.esoft.repository.entity.AccountEntity;
import com.minh.esoft.repository.entity.OrdersEntity;
import com.minh.esoft.repository.request.OrderCreateRequest;
import com.minh.esoft.repository.request.OrderUpdateRequest;
import com.minh.esoft.repository.response.OrderResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class OrderServiceUnitTest {

    @Mock
    OrdersRepository orderRepository;
    @Mock
    SecurityContextHolder securityContextHolder;
    @Mock
    Authentication authentication;
    @Mock
    SecurityContext securityContext;

    @InjectMocks
    OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

//        OrdersEntity order1 = mockOrderEntity(1L, "Edit Photo 1", null, null, OrderCategoryEnum.LUXURY, 1L, OrderStatusEnum.INITIAL, OrderServiceEnum.PHOTO_EDITING, 1L);
//        OrdersEntity order2 = mockOrderEntity(2L, "Edit Photo 2", null, null, OrderCategoryEnum.SUPER_LUXURY, 2L, OrderStatusEnum.CANCELLED, OrderServiceEnum.PHOTO_EDITING, 2L);
//        OrdersEntity order3 = mockOrderEntity(2L, "Edit Photo 3", null, null, OrderCategoryEnum.SUPREME_LUXURY, 3L, OrderStatusEnum.DONE, OrderServiceEnum.PHOTO_EDITING, 1L);
//        OrdersEntity order4 = mockOrderEntity(1L, "Edit Video 1", null, null, OrderCategoryEnum.LUXURY, 1L, OrderStatusEnum.PROCESS, OrderServiceEnum.VIDEO_EDITING, 2L);
//        OrdersEntity order5 = mockOrderEntity(2L, "Edit Video 2", null, null, OrderCategoryEnum.SUPER_LUXURY, 2L, OrderStatusEnum.SHIPPING, OrderServiceEnum.VIDEO_EDITING, 1L);
//        OrdersEntity order6 = mockOrderEntity(2L, "Edit Video 3", null, null, OrderCategoryEnum.SUPREME_LUXURY, 3L, OrderStatusEnum.INITIAL, OrderServiceEnum.VIDEO_EDITING, 2L);

//        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2, order3, order4, order5, order6));

    }

    private OrdersEntity mockOrderEntity(Long id, String name, String description, String notes, OrderCategoryEnum orderCategoryCode, Long quantity, OrderStatusEnum orderStatus, OrderServiceEnum orderServiceCode, Long userId) {
        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setId(id);
        ordersEntity.setName(name);
        ordersEntity.setDescription(description);
        ordersEntity.setNotes(notes);
        ordersEntity.setOrderCategoryCode(orderCategoryCode);
        ordersEntity.setQuantity(quantity);
        ordersEntity.setOrderStatus(orderStatus);
        ordersEntity.setOrderServiceCode(orderServiceCode);
        ordersEntity.setUserId(userId);
        return ordersEntity;
    }

    @Test
    void getOrder() {
    }

    @Test
    void createOrderSuccess() {
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest("Edit Photo", null, null, OrderCategoryEnum.LUXURY, 1L, OrderServiceEnum.PHOTO_EDITING);
        OrdersEntity order = mockOrderEntity(1L, "Edit Photo", null, null, OrderCategoryEnum.LUXURY, 1L, OrderStatusEnum.INITIAL, OrderServiceEnum.PHOTO_EDITING, 1L);

        when(orderRepository.save(any(OrdersEntity.class))).thenReturn(order);

        OrderResponse orderResponse = orderService.createOrder(orderCreateRequest);

        assertNotNull(orderResponse);
        assertEquals(order.getName(), orderResponse.getName());
        assertEquals(order.getOrderStatus(), orderResponse.getOrderStatus());
        assertEquals(order.getOrderServiceCode(), orderResponse.getOrderServiceCode());
    }

    @Test
    void getOrderByIdSuccess() throws DataNotFoundException, DataNotUpdateException, DataNotRelevantToUserException {
        OrdersEntity order = mockOrderEntity(1L, "Edit Photo", null, null, OrderCategoryEnum.LUXURY, 1L, OrderStatusEnum.INITIAL, OrderServiceEnum.PHOTO_EDITING, 1L);
        AccountEntity accountEntity = new AccountEntity(1L, "minh", "123456", "CUSTOMER", AccountStatusEnum.ACTIVE);
        when(orderRepository.findById(any(Long.class))).thenReturn(Optional.of(order));

        when(authentication.getPrincipal()).thenReturn(new JwtUserDetail(accountEntity));
        SecurityContextHolder.getContext().setAuthentication(authentication);


        OrderResponse orderResponse = orderService.getOrderById(1L);

        assertNotNull(orderResponse);
        assertEquals(order.getName(), orderResponse.getName());
        assertEquals(order.getOrderStatus(), orderResponse.getOrderStatus());
        assertEquals(order.getOrderServiceCode(), orderResponse.getOrderServiceCode());
        assertEquals(order.getUserId(), accountEntity.getId());
    }

    @Test
    void getOrderByIdNotFound() {
        when(orderRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(null));

        assertThatThrownBy(() -> orderService.getOrderById(1L))
                .isInstanceOf(DataNotFoundException.class);
    }

    @Test
    void getOrderByIdNotRelevantToUserException() {
        OrdersEntity order = mockOrderEntity(1L, "Edit Photo", null, null, OrderCategoryEnum.LUXURY, 1L, OrderStatusEnum.INITIAL, OrderServiceEnum.PHOTO_EDITING, 1L);
        AccountEntity accountEntity = new AccountEntity(2L, "minh", "123456", "CUSTOMER", AccountStatusEnum.ACTIVE);
        when(orderRepository.findById(any(Long.class))).thenReturn(Optional.of(order));

        when(authentication.getPrincipal()).thenReturn(new JwtUserDetail(accountEntity));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        assertThatThrownBy(() -> orderService.getOrderById(1L))
                .isInstanceOf(DataNotRelevantToUserException.class);
    }

    @Test
    void updateOrderNotFound() {
        OrderUpdateRequest orderUpdateRequest = new OrderUpdateRequest(null, null, OrderCategoryEnum.LUXURY, 2L, OrderServiceEnum.PHOTO_EDITING);
//        OrdersEntity order = mockOrderEntity(1L, "Edit Photo", null, null, OrderCategoryEnum.LUXURY, 1L, OrderStatusEnum.INITIAL, OrderServiceEnum.PHOTO_EDITING, 1L);

        when(orderRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(null));

        assertThatThrownBy(() -> orderService.updateOrder(1L, orderUpdateRequest))
                .isInstanceOf(DataNotFoundException.class);
    }

    @Test
    void updateOrderNotRelevantToUserException() {
        OrderUpdateRequest orderUpdateRequest = new OrderUpdateRequest(null, null, OrderCategoryEnum.LUXURY, 2L, OrderServiceEnum.PHOTO_EDITING);
        OrdersEntity order = mockOrderEntity(1L, "Edit Photo", null, null, OrderCategoryEnum.LUXURY, 1L, OrderStatusEnum.INITIAL, OrderServiceEnum.PHOTO_EDITING, 1L);
        AccountEntity accountEntity = new AccountEntity(2L, "minh", "123456", "CUSTOMER", AccountStatusEnum.ACTIVE);
        when(orderRepository.findById(any(Long.class))).thenReturn(Optional.of(order));

        when(authentication.getPrincipal()).thenReturn(new JwtUserDetail(accountEntity));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        assertThatThrownBy(() -> orderService.updateOrder(1L, orderUpdateRequest))
                .isInstanceOf(DataNotRelevantToUserException.class);
    }

    @Test
    void updateOrderNotUpdateException() {
        OrderUpdateRequest orderUpdateRequest = new OrderUpdateRequest(null, null, OrderCategoryEnum.LUXURY, 2L, OrderServiceEnum.PHOTO_EDITING);
        OrdersEntity order = mockOrderEntity(1L, "Edit Photo", null, null, OrderCategoryEnum.LUXURY, 1L, OrderStatusEnum.PROCESS, OrderServiceEnum.PHOTO_EDITING, 1L);
        AccountEntity accountEntity = new AccountEntity(1L, "minh", "123456", "CUSTOMER", AccountStatusEnum.ACTIVE);
        when(orderRepository.findById(any(Long.class))).thenReturn(Optional.of(order));

        when(authentication.getPrincipal()).thenReturn(new JwtUserDetail(accountEntity));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        assertThatThrownBy(() -> orderService.updateOrder(1L, orderUpdateRequest))
                .isInstanceOf(DataNotUpdateException.class);
    }

    @Test
    void deleteOrder() {
    }
}