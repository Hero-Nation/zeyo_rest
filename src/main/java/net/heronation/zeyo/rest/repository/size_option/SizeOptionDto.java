package net.heronation.zeyo.rest.repository.size_option;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.Value;

import net.heronation.zeyo.rest.repository.item_size_option_map.ItemSizeOptionMap;
import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;
import net.heronation.zeyo.rest.repository.kindof.Kindof;
import org.joda.time.DateTime;

@Data
public class SizeOptionDto {

 
	private Long id;

	private Long category;

	private Long subCategory;

	private Long kindof;

	private String name; 

}