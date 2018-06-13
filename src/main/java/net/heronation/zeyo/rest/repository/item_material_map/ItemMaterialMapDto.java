package net.heronation.zeyo.rest.repository.item_material_map;
 
import lombok.Value;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.kindof.Kindof;
import net.heronation.zeyo.rest.repository.material.Material;


@Value
public class ItemMaterialMapDto{
 
        private Long id;




private Item item;




private Kindof kindof;




private Material material;




private String contain;



private String useYn;
    
}