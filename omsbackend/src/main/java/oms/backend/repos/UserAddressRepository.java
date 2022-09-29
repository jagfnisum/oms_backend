package oms.backend.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import oms.backend.models.Address;



@Repository
public interface UserAddressRepository extends JpaRepository<Address, Integer>{

}
