package net.heronation.zeyo.rest.item_laundry_map.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.item.controller.Item;


@Data
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