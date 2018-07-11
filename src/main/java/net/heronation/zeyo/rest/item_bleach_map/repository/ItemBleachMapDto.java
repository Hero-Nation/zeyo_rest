package net.heronation.zeyo.rest.item_bleach_map.repository;
 
import lombok.Value;
import net.heronation.zeyo.rest.item.repository.Item;


@Value
public class ItemBleachMapDto{
 
        private Long id;




private Item item;




private String chlorine;



private String oxygen;



private String useYn;
    
}