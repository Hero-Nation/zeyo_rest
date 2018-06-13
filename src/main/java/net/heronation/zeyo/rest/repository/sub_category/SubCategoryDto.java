package net.heronation.zeyo.rest.repository.sub_category;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.Value;

import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.common.value.LIdVO;
import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.category.CategoryRepository;
import net.heronation.zeyo.rest.repository.sub_category_measure_map.SubCategoryMeasureMap;
import net.heronation.zeyo.rest.repository.sub_category_fit_info_map.SubCategoryFitInfoMap;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

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