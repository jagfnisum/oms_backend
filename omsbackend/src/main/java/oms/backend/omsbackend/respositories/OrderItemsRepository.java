package oms.backend.omsbackend.respositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import oms.backend.omsbackend.model.OrderItems;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems,Integer>{
    
}
