package net.heronation.zeyo.rest.item_scmm_so_value.repository;

import lombok.Value;
import net.heronation.zeyo.rest.item.repository.Item;
import net.heronation.zeyo.rest.size_option.repository.SizeOption;
import net.heronation.zeyo.rest.sub_category_measure_map.repository.SubCategoryMeasureMap;

@Value
public class ItemScmmSoValueDto {

	private Long id;

	private Item item;

	private SubCategoryMeasureMap subCategoryMeasureMap;

	private SizeOption sizeOption;

	private String inputValue;

	private String useYn;

}