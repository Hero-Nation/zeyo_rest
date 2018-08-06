package net.heronation.zeyo.rest.item_drycleaning_map.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.item.controller.Item;


@Data
public class ItemDrycleaningMapDto{
 
        private Long id;




private Item item;




private String drycan;



private String storecan;



private String detergent;



private String useYn;
    
}