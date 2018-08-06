package net.heronation.zeyo.rest.item_shopmall_map.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.item.controller.Item;import net.heronation.zeyo.rest.shopmall.controller.Shopmall;


@Data
public class ItemShopmallMapDto{
 
        private Long id;




private Item item;




private Shopmall shopmall;




private String useYn;
    
}