package net.heronation.zeyo.rest.repository.measure_item;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import lombok.Data;
import lombok.Value;
import net.heronation.zeyo.rest.repository.sub_category_measure_map.SubCategoryMeasureMap;

@Data
public class MeasureItemDto {

	private String name;

	private String metaDesc;

	public MeasureItem convertToEntity() {
		MeasureItem i = new MeasureItem();
		i.setName(name);
		i.setMetaDesc(metaDesc);
		i.setUseYn("Y");
		i.setCreateDt(new DateTime());
		return i;
	}
}