package net.heronation.zeyo.rest.repository.item_drycleaning_map;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.item.Item;


@Value
public class ItemDrycleaningMapDto{
 
        private Long id;




private Item item;




private String drycan;



private String storecan;



private String detergent;



private String useYn;
    
}