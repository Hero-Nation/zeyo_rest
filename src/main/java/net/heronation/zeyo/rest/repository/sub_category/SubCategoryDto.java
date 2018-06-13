package net.heronation.zeyo.rest.repository.sub_category;

import java.util.List;

import org.joda.time.DateTime;

import lombok.Data;
import net.heronation.zeyo.rest.common.value.LIdVO;

@Data
public class SubCategoryDto {
	 
	private Long id;
	private Long category; 

	private String name;

	private String itemImage;

	private String clothImage;

	private String laundryYn;

	private String drycleaningYn;

	private String ironingYn;

	private String drymethodYn;

	private String bleachYn;

 
	private List<LIdVO> measureItem;
	
	private List<LIdVO> fitinfos;
	 

	
	public SubCategory convertToEntity() {
		 
		
		SubCategory sc = new SubCategory();
		sc.setBleachYn(bleachYn); 
		sc.setClothImage(clothImage);
		sc.setCreateDt(new DateTime());
		sc.setDrycleaningYn(drycleaningYn);
		sc.setDrymethodYn(drymethodYn);
		sc.setIroningYn(ironingYn);
		sc.setItemImage(itemImage);
		sc.setLaundryYn(laundryYn);
		sc.setName(name);
		sc.setUseYn("Y");
		
		return sc;
	}
}