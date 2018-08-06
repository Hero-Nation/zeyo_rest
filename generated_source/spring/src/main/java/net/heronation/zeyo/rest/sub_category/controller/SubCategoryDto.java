package net.heronation.zeyo.rest.sub_category.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.item.controller.Item;
import net.heronation.zeyo.rest.category.controller.Category;import net.heronation.zeyo.rest.dmodel.controller.Dmodel;import net.heronation.zeyo.rest.sub_category_measure_map.controller.SubCategoryMeasureMap;
import net.heronation.zeyo.rest.sub_category_fit_info_map.controller.SubCategoryFitInfoMap;
import net.heronation.zeyo.rest.size_option.controller.SizeOption;
import net.heronation.zeyo.rest.v2_rule.controller.V2Rule;
import net.heronation.zeyo.rest.v2_rule.controller.V2Rule;


@Data
public class SubCategoryDto{
 
        private List<Item> items = new ArrayList<Item>();
 private Long id;




private Category category;




private Dmodel dmodel;




private String name;



private String itemImage;



private String clothImage;



private String laundryYn;



private String drycleaningYn;



private String ironingYn;



private String drymethodYn;



private String bleachYn;



private String createDt;



private String useYn;



private List<SubCategoryMeasureMap> subCategoryMeasureMaps = new ArrayList<SubCategoryMeasureMap>();
 private List<SubCategoryFitInfoMap> subCategoryFitInfoMaps = new ArrayList<SubCategoryFitInfoMap>();
 private List<SizeOption> sizeOptions = new ArrayList<SizeOption>();
 private List<V2Rule> v2Rules = new ArrayList<V2Rule>();
 private List<V2Rule> v2Rules = new ArrayList<V2Rule>();
    
}