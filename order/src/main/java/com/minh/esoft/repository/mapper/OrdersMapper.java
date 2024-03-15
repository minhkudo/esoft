package com.minh.esoft.repository.mapper;

import com.minh.esoft.common.ultils.DateTimeUltis;
import com.minh.esoft.repository.entity.OrdersEntity;
import com.minh.esoft.repository.request.OrderCreateRequest;
import com.minh.esoft.repository.request.OrderUpdateRequest;
import com.minh.esoft.repository.response.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = DateTimeUltis.class)
public interface OrdersMapper {
    OrdersMapper INSTANCE = Mappers.getMapper(OrdersMapper.class);

    OrdersEntity map2CreateOrderEntity(OrderCreateRequest orderCreateRequest);

    void mapToOrderEntity(@MappingTarget OrdersEntity ordersEntity, OrderUpdateRequest orderUpdateRequest);

    OrderResponse map2OrderResponse(OrdersEntity ordersEntity);

}
