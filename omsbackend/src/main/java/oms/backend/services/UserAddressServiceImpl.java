package oms.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oms.backend.exception.AddressAlreadyExistsException;
import oms.backend.exception.AddressNotFoundException;
import oms.backend.models.Address;
import oms.backend.repos.UserAddressRepository;


@Service
public class UserAddressServiceImpl implements UserAddressService{

	@Autowired
	UserAddressRepository repo;
	
	
	@Override
	public List<Address> getAll() {
		return repo.findAll();
	}


	@Override
	public Address getAddressById(Integer id) throws AddressNotFoundException {
		if(repo.findById(id).isEmpty()) {
			System.out.println("Department with id = " + id + " was not found");
			throw AddressNotFoundException.createWith(id);
		}else {
			return repo.findById(id).get();
		}
	}


	@Override
	public Boolean deleteAddressById(Integer id) throws AddressNotFoundException {
		if(repo.findById(id).isEmpty()) {
			throw AddressNotFoundException.createWith(id);
		}else {
			repo.deleteById(id);
			return true;
		}
	}


	@Override
	public Address updateAddress(Integer id, Address address) throws AddressNotFoundException {
		if(repo.findById(id).isEmpty()) {
			throw AddressNotFoundException.createWith(id);
		}
		Address saved = repo.save(address);
		return saved;
	}


	@Override
	public Address createAddress(Address address) throws AddressAlreadyExistsException {
		//Checking to see if id is null because it's auto incremented in the DB and the user may not provide an id
		if(address.getAddress_id() != null) {
			if(repo.existsById(address.getAddress_id())) {
				throw AddressAlreadyExistsException.createWith(address.getAddress_id());
			}
		}
		
		return repo.save(address);
	}


//	@Override
//	public List<Address> getAddressByUserID(Integer userID) {
//		repo.findAll(userID);
//	}

}
