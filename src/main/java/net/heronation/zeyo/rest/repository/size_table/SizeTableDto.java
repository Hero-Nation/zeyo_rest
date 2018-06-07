package net.heronation.zeyo.rest.repository.size_table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.Value;

import net.heronation.zeyo.rest.repository.item.Item;
import org.joda.time.DateTime;

@Data
public class SizeTableDto {

	private Long id;

	private Item item;

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
	
	private String target;
	
	public SizeTable convertToEntity() {
		SizeTable st = new SizeTable();
		
		if(id != null) st.setId(id);
		if(item != null) st.setItem(item);
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
		if(useYn != null) st.setUseYn(useYn); 
		
		return st;
	}

}