package oms.backend.services;

import java.util.List;

import oms.backend.exception.AddressAlreadyExistsException;
import oms.backend.exception.AddressNotFoundException;
import oms.backend.models.Address;



public interface UserAddressService {
	List<Address> getAll();
	//List<Address> getAddressByUserID(Integer userID);
	Address getAddressById(Integer id) throws AddressNotFoundException;
	Boolean deleteAddressById(Integer id) throws AddressNotFoundException;
	Address updateAddress(Integer id, Address address) throws AddressNotFoundException;
	Address createAddress(Address address) throws AddressAlreadyExistsException;
	
}
	