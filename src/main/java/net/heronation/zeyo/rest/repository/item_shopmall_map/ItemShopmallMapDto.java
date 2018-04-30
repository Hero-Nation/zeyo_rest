package net.heronation.zeyo.rest.repository.item_shopmall_map;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.item.Item;import net.heronation.zeyo.rest.repository.shopmall.Shopmall;


@Value
public class ItemShopmallMapDto{
 
        private Long id;




private Item item;




private Shopmall shopmall;




private String useYn;
    
}