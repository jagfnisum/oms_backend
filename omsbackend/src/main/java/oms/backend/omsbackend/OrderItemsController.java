package oms.backend.omsbackend;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oms.backend.omsbackend.model.OrderItems;
import oms.backend.omsbackend.service.OrderItemsSerivce;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    
    /**
     * 
     * A method to update an item's quantity within an order
     * @param orderid the id of the order to be updated
     * @param upc the product id of the product to be udpated
     * @param updatedQuantity the amount we want to update our item to
     * @return A response entity that specifies whether or not we were
     * able to update the item in the database. If not, we will return a 
     * bad request http status
     */
    @PatchMapping("updateItemQuantity/{orderid}/{upc}/{updatedQuantity}")
    public ResponseEntity<String> updateItem(@PathVariable("orderid") int orderid,
     @PathVariable("upc") String upc, @PathVariable("updatedQuantity") int updatedQuantity){
        boolean updated = service.updateOrderItem(orderid, upc, updatedQuantity);
        if(updated){
            return ResponseEntity.status(HttpStatus.OK).body("Item "+upc+" updated to "+ updatedQuantity);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
     }
}
