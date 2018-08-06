package net.heronation.zeyo.rest.item_material_map.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.item.controller.Item;import net.heronation.zeyo.rest.kindof.controller.Kindof;import net.heronation.zeyo.rest.material.controller.Material;


@Data
public class ItemMaterialMapDto{
 
        private Long id;




private Item item;




private Kindof kindof;




private Material material;




private String contain;



private String useYn;
    
}