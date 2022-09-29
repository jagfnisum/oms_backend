package oms.backend.services;

import java.util.List;

import oms.backend.models.CreditCard;

public interface CreditCardService {
    List <CreditCard> getAll();
    CreditCard getCreditCardByID(Integer creditCardID) throws Exception;
    CreditCard createCreditCard(CreditCard creditCard) throws Exception;
    CreditCard updateCreditCard(Integer creditCardID, CreditCard creditCard) throws Exception;
    boolean deleteCreditCardByID(Integer creditCardID)throws Exception;
}
