package net.heronation.zeyo.rest.item_ironing_map.repository;
 
import lombok.Value;
import net.heronation.zeyo.rest.item.repository.Item;


@Value
public class ItemIroningMapDto{
 
        private Long id;




private Item item;




private String ironcan;



private String addprotection;



private String startTemp;



private String endTemp;



private String useYn;
    
}