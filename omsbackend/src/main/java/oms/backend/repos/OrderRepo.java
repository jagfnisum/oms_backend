package oms.backend.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import oms.backend.models.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
}
