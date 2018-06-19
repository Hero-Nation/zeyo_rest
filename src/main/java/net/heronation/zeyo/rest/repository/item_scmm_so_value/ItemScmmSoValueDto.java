package net.heronation.zeyo.rest.repository.item_scmm_so_value;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.sub_category_measure_map.SubCategoryMeasureMap;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;

@Value
public class ItemScmmSoValueDto {

	private Long id;

	private Item item;

	private SubCategoryMeasureMap subCategoryMeasureMap;

	private SizeOption sizeOption;

	private String inputValue;

	private String useYn;

}