package oms.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oms.backend.models.OrderItems;
import oms.backend.repos.OrderItemsRepository;

@Service
public class OrderItemsServiceImpl implements OrderItemsSerivce{
    @Autowired
    OrderItemsRepository repo;
    /**
     * This is a method that takes in an orderid and finds
     * all items included in that order. It returns a list of
     * order items;
     * @param id The actual ID of the order to be queried
     * @return a list of order items from the queried order id
     */
    @Override
    public List<OrderItems> getAllItemsInOrder(int id) {
        List<OrderItems> items = new ArrayList<OrderItems>();
        List<OrderItems> exist = repo.findAll();
        if(exist.isEmpty()){
            return exist;
        }else{
            for(OrderItems item:exist){
                if(item.getOrder().getOrderID() == id) {
                    items.add(item);
                }
            }
        }
        return items;
    }

    /**
     * This is a method that takes in an orderid, upc, and an amount
     * to update for an item. If it finds the item in our database, it
     * will be updated, otherwise the method will fail if there is no 
     * item in the database to update.
     * @param orderid The acutal ID of the order
     * @param upc The ID of the product to be updated
     * @param updatedQuantity The new quantity of the item to be purchased
     * @return boolean(true,false) depending on whether or not the update was successful
     */
    @Override
    public boolean updateOrderItem(int orderid, String upc, int updatedQuantity) {
        List<OrderItems> exist = repo.findAll();
        if(exist.isEmpty()){
            return false;
        }else{
            for(OrderItems current:exist){
                if(current.getOrder().getOrderID()==orderid && current.getUpc().equals(upc)){    
                    current.setQuantity(updatedQuantity);
                    repo.save(current);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This is a method that takes in a new item and adds it to the database
     * IF it doesn't already exist
     * @param item A new item to be added to the database
     * @return boolean(true,false) depending on whether or not the action was successful
     */
    @Override
    public boolean addOrderItem(OrderItems item) {
        Optional<OrderItems> doesItemExist = repo.findById(item.getOrderItemid());
        if (doesItemExist.isPresent()) {
            return false;
        }
        repo.save(item);
        return true;
    }

}
