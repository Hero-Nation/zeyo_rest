package net.heronation.zeyo.rest.size_table.repository;

import org.joda.time.DateTime;

import lombok.Data;

@Data
public class SizeTableDto {
	 

	private Long id;
  
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
	
	private String file;
	
	
	public SizeTable convertToEntity() {
		SizeTable st = new SizeTable();
		
		if(id != null) st.setId(id);  
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
		if(file !=null) st.setTable_image(file.concat(".png"));
		
		return st;
	}

}