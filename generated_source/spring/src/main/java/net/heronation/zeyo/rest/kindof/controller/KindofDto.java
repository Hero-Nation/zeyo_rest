package net.heronation.zeyo.rest.kindof.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.item_material_map.controller.ItemMaterialMap;
import net.heronation.zeyo.rest.madein.controller.Madein;
import net.heronation.zeyo.rest.warranty.controller.Warranty;
import net.heronation.zeyo.rest.size_option.controller.SizeOption;
import net.heronation.zeyo.rest.cloth_color.controller.ClothColor;
import net.heronation.zeyo.rest.bbs.controller.Bbs;


@Data
public class KindofDto{
 
        private List<ItemMaterialMap> itemMaterialMaps = new ArrayList<ItemMaterialMap>();
 private List<Madein> madeins = new ArrayList<Madein>();
 private List<Warranty> warrantys = new ArrayList<Warranty>();
 private List<SizeOption> sizeOptions = new ArrayList<SizeOption>();
 private List<ClothColor> clothColors = new ArrayList<ClothColor>();
 private List<Bbs> bbss = new ArrayList<Bbs>();
 private Long id;




private String ktype;



private String kvalue;



private String useYn;
    
}