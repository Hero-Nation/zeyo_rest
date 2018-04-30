package net.heronation.zeyo.rest.repository.cloth_color;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.item_cloth_color_map.ItemClothColorMap;
import net.heronation.zeyo.rest.repository.kindof.Kindof;


@Value
public class ClothColorDto{
 
        private List<ItemClothColorMap> itemClothColorMaps = new ArrayList<ItemClothColorMap>();
 private Long id;




private Kindof kindof;




private String name;



private String useYn;
    
}