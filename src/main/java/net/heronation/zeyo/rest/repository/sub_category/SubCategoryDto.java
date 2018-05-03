package net.heronation.zeyo.rest.repository.sub_category;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.category.Category;import net.heronation.zeyo.rest.repository.sub_category_measure_map.SubCategoryMeasureMap;
import net.heronation.zeyo.rest.repository.sub_category_fit_info_map.SubCategoryFitInfoMap;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;


@Value
public class SubCategoryDto{
 
        private List<Item> items = new ArrayList<Item>();
 private Long id;




private Category category;




private String name;



private String itemImage;



private String clothImage;



private String laundryYn;



private String drycleaningYn;



private String ironingYn;



private String drymethodYn;



private String bleachYn;



 
private Date createDt;



private String useYn;



private List<SubCategoryMeasureMap> subCategoryMeasureMaps = new ArrayList<SubCategoryMeasureMap>();
 private List<SubCategoryFitInfoMap> subCategoryFitInfoMaps = new ArrayList<SubCategoryFitInfoMap>();
 private List<SizeOption> sizeOptions = new ArrayList<SizeOption>();
    
}