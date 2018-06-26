package net.heronation.zeyo.rest.repository.sub_category;

import java.util.List;

import org.joda.time.DateTime;

import lombok.Data;
import net.heronation.zeyo.rest.common.value.FileDto;
import net.heronation.zeyo.rest.common.value.LIdMapIdDto;
import net.heronation.zeyo.rest.common.value.LIdDto;

@Data
public class SubCategoryDto {

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

		return sc;
	}
}