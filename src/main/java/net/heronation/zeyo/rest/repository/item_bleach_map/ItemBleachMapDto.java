package net.heronation.zeyo.rest.repository.item_bleach_map;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.item.Item;


@Value
public class ItemBleachMapDto{
 
        private Long id;




private Item item;




private String chlorine;



private String oxygen;



private String useYn;
    
}