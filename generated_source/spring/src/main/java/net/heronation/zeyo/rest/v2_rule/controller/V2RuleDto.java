package net.heronation.zeyo.rest.v2_rule.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.sub_category.controller.SubCategory;import net.heronation.zeyo.rest.sub_category.controller.SubCategory;


@Data
public class V2RuleDto{
 
        private Long id;




private String title;



private String ruleType;



private String ruleMessage;



private SubCategory subCategory;




private String firstIncludeChild;



private SubCategory subCategory;




private String secondIncludeChild;



private DateTime  createDt;



private String useYn;
    
}