package net.heronation.zeyo.rest.material.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.item_material_map.controller.ItemMaterialMap;


@Data
public class MaterialDto{
 
        private List<ItemMaterialMap> itemMaterialMaps = new ArrayList<ItemMaterialMap>();
 private Long id;




private String name;



private String image;



private String metaDesc;



private String createDt;



private String useYn;
    
}