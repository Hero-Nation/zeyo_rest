package net.heronation.zeyo.rest.measure_item.repository;

import org.joda.time.DateTime;

import lombok.Data;

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