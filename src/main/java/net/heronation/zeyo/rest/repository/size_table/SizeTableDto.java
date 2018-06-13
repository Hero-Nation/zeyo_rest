package net.heronation.zeyo.rest.repository.size_table;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.item.ItemRepository;

@Data
public class SizeTableDto {
	
	@Autowired
	ItemRepository itemRepo;

	private Long id;

	private Item item;
	
	private Long item_id;

	private String visibleNameYn;

	private String visibleCodeYn;

	private String visibleBasicYn;

	private String visibleItemImageYn;

	private String visibleColorYn;

	private String visibleMeasureTableYn;

	private String visibleLaundryInfoYn;

	private String visibleMeasureHowAYn;

	private String visibleMeasureHowBYn;

	private String visibleFitInfoYn;

	private DateTime createDt;

	private String useYn;
	 
	
	public SizeTable convertToEntity() {
		SizeTable st = new SizeTable();
		
		if(id != null) st.setId(id);
		if(item != null) st.setItem(item);
		if(item_id != null) st.setItem(itemRepo.findOne(item_id));
		if(visibleNameYn != null) st.setVisibleNameYn(visibleNameYn);
		if(visibleCodeYn != null) st.setVisibleCodeYn(visibleCodeYn);
		if(visibleBasicYn != null) st.setVisibleBasicYn(visibleBasicYn);
		if(visibleItemImageYn != null) st.setVisibleItemImageYn(visibleItemImageYn);
		if(visibleColorYn != null) st.setVisibleColorYn(visibleColorYn);
		if(visibleMeasureTableYn != null) st.setVisibleMeasureTableYn(visibleMeasureTableYn);
		if(visibleLaundryInfoYn != null) st.setVisibleLaundryInfoYn(visibleLaundryInfoYn);
		if(visibleMeasureHowAYn != null) st.setVisibleMeasureHowAYn(visibleMeasureHowAYn);
		if(visibleMeasureHowBYn != null) st.setVisibleMeasureHowBYn(visibleMeasureHowBYn);
		if(visibleFitInfoYn != null) st.setVisibleFitInfoYn(visibleFitInfoYn);
		if(createDt != null) st.setCreateDt(createDt);
		if(createDt == null) st.setCreateDt(new DateTime());
		if(useYn != null) st.setUseYn(useYn);
		if(useYn == null) st.setUseYn("Y"); 
		
		return st;
	}

}