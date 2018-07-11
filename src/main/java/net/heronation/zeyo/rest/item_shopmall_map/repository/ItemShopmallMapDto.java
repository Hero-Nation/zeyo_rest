package net.heronation.zeyo.rest.item_shopmall_map.repository;
 
import lombok.Value;
import net.heronation.zeyo.rest.item.repository.Item;
import net.heronation.zeyo.rest.shopmall.repository.Shopmall;


@Value
public class ItemShopmallMapDto{
 
        private Long id;




private Item item;




private Shopmall shopmall;




private String useYn;
    
}