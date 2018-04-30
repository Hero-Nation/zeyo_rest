package net.heronation.zeyo.rest.repository.item_laundry_map;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.item.Item;


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