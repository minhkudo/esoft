package com.minh.esoft.repository;

import com.minh.esoft.repository.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Long>, PagingAndSortingRepository<OrdersEntity, Long>, JpaSpecificationExecutor<OrdersEntity> {
}
