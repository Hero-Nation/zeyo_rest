package net.heronation.zeyo.rest.dmodel.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.dmodel.repository.Dmodel;
import net.heronation.zeyo.rest.dmodel.repository.DmodelRepository;
import net.heronation.zeyo.rest.dmodel_measure_map.repository.DmodelMeasureMap;
import net.heronation.zeyo.rest.dmodel_measure_map.repository.DmodelMeasureMapRepository;
import net.heronation.zeyo.rest.dmodel_ratio.repository.DmodelRatio;
import net.heronation.zeyo.rest.dmodel_ratio.repository.DmodelRatioRepository;
import net.heronation.zeyo.rest.item_size_option_map.repository.ItemSizeOptionMap;
import net.heronation.zeyo.rest.measure_item.repository.MeasureItem;
import net.heronation.zeyo.rest.measure_item.repository.MeasureItemRepository;
import net.heronation.zeyo.rest.size_option.repository.SizeOption;
import net.heronation.zeyo.rest.sub_category.repository.SubCategory;
import net.heronation.zeyo.rest.sub_category.repository.SubCategoryRepository;

@Slf4j
public class DmodelDtoDeserializer extends JsonDeserializer<DmodelDto> {

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private DmodelMeasureMapRepository dmodelMeasureMapRepository;
	
	@Autowired
	private DmodelRepository dmodelRepository;
	
	@Autowired
	private MeasureItemRepository measureItemRepository; 
	@Autowired
	private DmodelRatioRepository dmodelRatioRepository;

	@Override
	public DmodelDto deserialize(JsonParser jsonParser, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		
		log.debug("DmodelDtoDeserializer : deserialize"); 
		log.debug("subCategoryRepository : " +(subCategoryRepository == null)+"");	  
		log.debug("dmodelMeasureMapRepository : " +(dmodelMeasureMapRepository == null)+"");	 
		log.debug("dmodelRepository : " +(dmodelRepository == null)+"");	  
		log.debug("measureItemRepository : " +(measureItemRepository == null)+"");	 
		log.debug("dmodelRatioRepository : " +(dmodelRatioRepository == null)+"");	 	
		
		

		JsonNode root_node = jsonParser.getCodec().readTree(jsonParser);

		Long dmodel_id = root_node.get("id").asLong();

		Dmodel this_model = new Dmodel();
		if(dmodel_id != 0) {
			this_model.setId(dmodel_id);	
		}
		
		
		String title = root_node.get("title").asText();

		String controller = root_node.get("controller").asText();

		String svgdata = root_node.get("svgdata").asText();

		String useYn = root_node.get("use_yn").asText();

		Iterator<JsonNode> subCategorys_node = root_node.get("sub_category").elements();
		List<SubCategory> subCategorys = new ArrayList<SubCategory>();
		
		while (subCategorys_node.hasNext()) {
			JsonNode sc_child = subCategorys_node.next();
			
			long sc_id = sc_child.asLong();
			log.debug("sub_category  "+sc_id);	 
			
			SubCategory this_sc = new SubCategory();
			this_sc.setId(sc_id);
			subCategorys.add(this_sc);
			

		}
		
		
		
		Iterator<JsonNode> dmodelMeasureMaps_node = root_node.get("measure_item").elements();
		List<DmodelMeasureMap> dmodelMeasureMaps = new ArrayList<DmodelMeasureMap>();
		
		while (dmodelMeasureMaps_node.hasNext()) {
			JsonNode dmm_child = dmodelMeasureMaps_node.next();
			
			long mm_id = dmm_child.get("id").asLong();
			long mm_map_id = dmm_child.get("map_id").asLong();
			String mm_min = dmm_child.get("min").asText();
			String mm_max = dmm_child.get("max").asText();
			
			log.debug("measure_item  mm_id "+mm_id);
			log.debug("measure_item  mm_map_id "+mm_map_id);
			
			log.debug("measure_item  min "+mm_min);
			log.debug("measure_item  max "+mm_max);
			
			MeasureItem this_mi = new MeasureItem();
			this_mi.setId(mm_id);

			DmodelMeasureMap this_dmm = new DmodelMeasureMap();
			this_dmm.setMaxValue(mm_max);
			this_dmm.setMinValue(mm_min);
			this_dmm.setUseYn("Y");
			this_dmm.setMeasureItem(this_mi);
			this_dmm.setDmodel(this_model);
			if(mm_map_id != 0) {
				this_dmm.setId(mm_map_id);
			} 
			
			dmodelMeasureMaps.add(this_dmm);
			

			 
			
		}
		
		
		
		Iterator<JsonNode> dmodelRatios_node = root_node.get("width_ratio").elements();
		List<DmodelRatio> dmodelRatios = new ArrayList<DmodelRatio>();
		
		while (dmodelRatios_node.hasNext()) {
			JsonNode dr_child = dmodelRatios_node.next();
			 
			String mm_ratio = dr_child.get("ratio").asText();
			String mm_min = dr_child.get("min").asText();
			String mm_max = dr_child.get("max").asText();
			long mm_id = dr_child.get("id").asLong();
			
			 
			log.debug("measure_item  mm_ratio "+mm_ratio); 
			log.debug("measure_item  min "+mm_min);
			log.debug("measure_item  max "+mm_max);
			log.debug("measure_item  mm_id "+mm_id);
			
			DmodelRatio this_ds = new DmodelRatio();
			this_ds.setDefaultYn("N");
			this_ds.setMaxValue(mm_max);
			this_ds.setMinValue(mm_min);
			this_ds.setRatioValue(mm_ratio);
			if(mm_id != 0) {
				this_ds.setId(mm_id);	
			}
			
			this_ds.setUseYn("Y");
			
			dmodelRatios.add(this_ds); 
		}
		
		
		DmodelDto R = new DmodelDto();
		R.setId(dmodel_id);
		R.setTitle(title);
		R.setController(controller);
		R.setSvgdata(svgdata);
		R.setUseYn(useYn);
		R.setCreateDt(new DateTime());
		R.setUpdateDt(new DateTime());
		R.setSubCategorys(subCategorys);
		R.setDmodelMeasureMaps(dmodelMeasureMaps);
		R.setDmodelRatios(dmodelRatios);
		return R;
	}

}
