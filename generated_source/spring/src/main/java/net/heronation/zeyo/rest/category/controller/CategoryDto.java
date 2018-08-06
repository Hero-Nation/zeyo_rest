package net.heronation.zeyo.rest.category.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.item.controller.Item;
import net.heronation.zeyo.rest.sub_category.controller.SubCategory;
import net.heronation.zeyo.rest.size_option.controller.SizeOption;


@Data
public class CategoryDto{
 
        private List<Item> items = new ArrayList<Item>();
 private Long id;




private String name;



private String createDt;



private String useYn;



private List<SubCategory> subCategorys = new ArrayList<SubCategory>();
 private List<SizeOption> sizeOptions = new ArrayList<SizeOption>();
    
}