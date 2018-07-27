package net.heronation.zeyo.rest.sub_category.controller;

import java.util.List;

import org.joda.time.DateTime;

import lombok.Data;
import net.heronation.zeyo.rest.common.dto.FileDto;
import net.heronation.zeyo.rest.common.dto.LIdMapIdDto;
import net.heronation.zeyo.rest.sub_category.repository.SubCategory;

@Data
public class V2CategoryDto {
	private Long id;
	private Long category;

	private String name;

	private String laundryYn;

	private String drycleaningYn;

	private String ironingYn;

	private String drymethodYn;

	private String bleachYn;

	private List<LIdMapIdDto> measureItem;

	private List<LIdMapIdDto> fitinfos;

	private List<FileDto> itemImage;

	private List<FileDto> clothImage;

	private Long parent_id;

	public SubCategory convertToEntity() {

		SubCategory sc = new SubCategory();
		sc.setBleachYn(bleachYn);
		if (clothImage != null && clothImage.size() > 0)
			sc.setClothImage(clothImage.get(0).getReal_name());
		sc.setCreateDt(new DateTime());
		sc.setDrycleaningYn(drycleaningYn);
		sc.setDrymethodYn(drymethodYn);
		sc.setIroningYn(ironingYn);
		if (itemImage != null && itemImage.size() > 0)
			sc.setItemImage(itemImage.get(0).getReal_name());
		sc.setLaundryYn(laundryYn);
		sc.setName(name);
		sc.setUseYn("Y");
		sc.setParentId(parent_id);

		return sc;
	}
}
