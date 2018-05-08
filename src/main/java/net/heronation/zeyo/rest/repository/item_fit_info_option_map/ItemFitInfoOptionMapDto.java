package net.heronation.zeyo.rest.repository.item_fit_info_option_map;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.item.Item;import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOption;


@Value
public class ItemFitInfoOptionMapDto{
 
        private Long id;




private Item item;




private FitInfoOption fitInfoOption;




private String useYn;
    
}