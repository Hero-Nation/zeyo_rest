package net.heronation.zeyo.rest.item_laundry_map.repository;
 
import lombok.Value;
import net.heronation.zeyo.rest.item.repository.Item;


@Value
public class ItemLaundryMapDto{
 
        private Long id;




private Item item;




private String water;



private String machine;



private String hand;



private String waterTemp;



private String intensity;



private String detergent;



private String useYn;
    
}