package net.heronation.zeyo.rest.item_drycleaning_map.repository;
 
import lombok.Value;
import net.heronation.zeyo.rest.item.repository.Item;


@Value
public class ItemDrycleaningMapDto{
 
        private Long id;




private Item item;




private String drycan;



private String storecan;



private String detergent;



private String useYn;
    
}