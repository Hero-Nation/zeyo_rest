package net.heronation.zeyo.rest.repository.category;

import java.util.ArrayList;
import java.util.List;

import lombok.Value;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;

@Value
public class CategoryDto {

	private List<Item> items = new ArrayList<Item>();
	private Long id;

	private String name;

	private String createDt;

	private String useYn;
	
	private Long parent_id;

	private List<SubCategory> subCategorys = new ArrayList<SubCategory>();
	private List<SizeOption> sizeOptions = new ArrayList<SizeOption>();

}