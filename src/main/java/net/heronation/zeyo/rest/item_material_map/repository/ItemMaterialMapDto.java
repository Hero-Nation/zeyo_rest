package net.heronation.zeyo.rest.item_material_map.repository;
 
import lombok.Value;
import net.heronation.zeyo.rest.item.repository.Item;
import net.heronation.zeyo.rest.kindof.repository.Kindof;
import net.heronation.zeyo.rest.material.repository.Material;


@Value
public class ItemMaterialMapDto{
 
        private Long id;




private Item item;




private Kindof kindof;




private Material material;




private String contain;



private String useYn;
    
}