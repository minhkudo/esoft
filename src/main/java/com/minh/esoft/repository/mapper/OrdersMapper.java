package com.minh.esoft.repository.mapper;

import com.minh.esoft.common.enums.OrderStatusEnum;
import com.minh.esoft.repository.entity.OrdersEntity;
import com.minh.esoft.repository.request.OrderRequest;
import com.minh.esoft.repository.response.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrdersMapper {
    OrdersMapper INSTANCE = Mappers.getMapper(OrdersMapper.class);

    OrdersEntity map2CreateOrderEntity(OrderRequest orderRequest);
    OrderResponse map2OrderResponse(OrdersEntity ordersEntity);

}
