package net.heronation.zeyo.rest.sub_category_fit_info_map.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.sub_category.controller.SubCategory;import net.heronation.zeyo.rest.fit_info.controller.FitInfo;


@Data
public class SubCategoryFitInfoMapDto{
 
        private Long id;




private SubCategory subCategory;




private FitInfo fitInfo;




private String useYn;
    
}