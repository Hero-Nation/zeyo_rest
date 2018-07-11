package net.heronation.zeyo.rest.category.repository;

import java.util.ArrayList;
import java.util.List;

import lombok.Value;
import net.heronation.zeyo.rest.item.repository.Item;
import net.heronation.zeyo.rest.size_option.repository.SizeOption;
import net.heronation.zeyo.rest.sub_category.repository.SubCategory;

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