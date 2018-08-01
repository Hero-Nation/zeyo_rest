package net.heronation.zeyo.rest.dmodel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import net.heronation.zeyo.rest.dmodel.repository.Dmodel;
import net.heronation.zeyo.rest.dmodel_measure_map.repository.DmodelMeasureMap;
import net.heronation.zeyo.rest.dmodel_ratio.repository.DmodelRatio;
import net.heronation.zeyo.rest.sub_category.repository.SubCategory;

@Data
@AllArgsConstructor
@JsonDeserialize(using = DmodelDtoDeserializer.class)
public class DmodelDto {

	public DmodelDto() {
		// TODO Auto-generated constructor stub
	} 
	private Long id;
 
	private String title;
 
	private String controller;
 
	private String svgdata;
 
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;
 
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime updateDt;
 
	private String useYn;
 
	private List<SubCategory> subCategorys;
	private List<DmodelMeasureMap> dmodelMeasureMaps;
	private List<DmodelRatio> dmodelRatios;
	
	public Dmodel getAsEntity() {
		
		Dmodel R = new Dmodel();
		R.setId(id);
		R.setController(controller);
		R.setCreateDt(createDt);
		R.setDmodelMeasureMaps(dmodelMeasureMaps);
		R.setDmodelRatios(dmodelRatios);
		R.setSubCategorys(subCategorys);
		R.setSvgdata(svgdata);
		R.setTitle(title);
		R.setUpdateDt(updateDt);
		R.setUseYn(useYn);
		
		return R;
		
	}

}