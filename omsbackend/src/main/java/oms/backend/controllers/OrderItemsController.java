package oms.backend.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oms.backend.models.OrderItems;
import oms.backend.services.OrderItemsSerivce;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("api/orderitems")
@RestController
public class OrderItemsController {
    @Autowired
    OrderItemsSerivce service;

    /**
     * A method to get every item included in a specified order;
     * @param orderid the id of the order to be checked
     * @return A response entity that specifies whether or not the order
     * is in the database. If the order is in the database, we will return
     * a response body filled with the item list of that order
     */
    @GetMapping("/getOrderInfo/{orderid}")
    public ResponseEntity<List<OrderItems>> getOrderInfo(@PathVariable("orderid") int orderid) {
        List<OrderItems> itemList=service.getAllItemsInOrder(orderid);
        if(itemList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(itemList);
    }
    

    //Old Patch method that does not work for our current database
    //Uncomment if required to use PatchMapping instead of PutMapping

    // /**
    //  * 
    //  * A method to update an item's quantity within an order
    //  * @param orderid the id of the order to be updated
    //  * @param upc the product id of the product to be udpated
    //  * @param updatedQuantity the amount we want to update our item to
    //  * @return A response entity that specifies whether or not we were
    //  * able to update the item in the database. If not, we will return a 
    //  * bad request http status
    //  */
    // @PatchMapping("/updateItemQuantity/{orderid}/{upc}/{updatedQuantity}")
    // public ResponseEntity<String> updateItem(@PathVariable("orderid") int orderid,
    //  @PathVariable("upc") String upc, @PathVariable("updatedQuantity") int updatedQuantity){
    //     boolean updated = service.updateOrderItem(orderid, upc, updatedQuantity);
    //     if(updated){
    //         return ResponseEntity.status(HttpStatus.OK).body("Item "+upc+" updated to "+ updatedQuantity);
    //     }
    //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    // }


    /**
     * A method that updates an item entry in our orderitems
     * database. Please refer to the following JSON format when
     * trying to update an item in the database
     * Example Postman Entry
     * {
            "orderid": 6,
            "quantity": 10,
            "upc": "100000001111",
            "order_itemid": 1
        }
     * @param item An item to be updated
     * @return A status that specifies whether or not the item
     * was successfully updated. OK for if updated correctly, Bad Request 
     * for failed update.
     */
    @PutMapping(value="updateItem")
    public ResponseEntity<OrderItems> updateItem(@RequestBody OrderItems item){
        boolean updated = service.updateOrderItem(item.getOrderid(), item.getUpc(), item.getQuantity());
        if(updated==true){
            return ResponseEntity.status(HttpStatus.OK).body(item);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
