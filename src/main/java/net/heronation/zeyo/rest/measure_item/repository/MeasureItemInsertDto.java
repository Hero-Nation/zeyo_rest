package net.heronation.zeyo.rest.measure_item.repository;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import lombok.Value;
import net.heronation.zeyo.rest.sub_category_measure_map.repository.SubCategoryMeasureMap;

@Value
public class MeasureItemInsertDto {

	private List<SubCategoryMeasureMap> subCategoryMeasureMaps = new ArrayList<SubCategoryMeasureMap>();
	private Long id;

	private String name;

	private String metaDesc;
 
	private DateTime createDt;

	private String useYn;

}