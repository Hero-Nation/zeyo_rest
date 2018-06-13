package net.heronation.zeyo.rest.repository.item_fit_info_option_map;
 
import lombok.Value;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOption;
import net.heronation.zeyo.rest.repository.item.Item;


@Value
public class ItemFitInfoOptionMapDto{
 
        private Long id;




private Item item;




private FitInfoOption fitInfoOption;




private String useYn;
    
}