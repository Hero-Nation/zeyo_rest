package net.heronation.zeyo.rest.sub_category_fit_info_map.repository;
 
import lombok.Value;
import net.heronation.zeyo.rest.fit_info.repository.FitInfo;
import net.heronation.zeyo.rest.sub_category.repository.SubCategory;


@Value
public class SubCategoryFitInfoMapDto{
 
        private Long id;




private SubCategory subCategory;




private FitInfo fitInfo;




private String useYn;
    
}