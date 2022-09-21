package oms.backend.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import oms.backend.models.OrderItems;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems,Integer>{
    
}
