package net.heronation.zeyo.rest.dmodel.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.dmodel_measure_map.repository.DmodelMeasureMap;
import net.heronation.zeyo.rest.dmodel_measure_map.repository.DmodelMeasureMapRepository;
import net.heronation.zeyo.rest.dmodel_ratio.repository.DmodelRatio;
import net.heronation.zeyo.rest.dmodel_ratio.repository.DmodelRatioRepository;
import net.heronation.zeyo.rest.sub_category.repository.SubCategory;
import net.heronation.zeyo.rest.sub_category.repository.SubCategoryRepository;

@Slf4j
public class DmodelDtoDeserializer extends JsonDeserializer<DmodelDto> {

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private DmodelMeasureMapRepository dmodelMeasureMapRepository;

	@Autowired
	private DmodelRatioRepository dmodelRatioRepository;

	@Override
	public DmodelDto deserialize(JsonParser jsonParser, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		log.debug("DmodelDtoDeserializer : deserialize");

		JsonNode root_node = jsonParser.getCodec().readTree(jsonParser);

		Long dmodel_id = root_node.get("id").asLong();

		String title = root_node.get("title").asText();

		String controller = root_node.get("controller").asText();

		String svgdata = root_node.get("svgdata").asText();

		String useYn = root_node.get("use_yn").asText();

		DmodelDto R = new DmodelDto();
		R.setId(dmodel_id);
		R.setTitle(title);
		R.setController(controller);
		R.setSvgdata(svgdata);
		R.setUseYn(useYn);
		R.setCreateDt(new DateTime());
		R.setUpdateDt(new DateTime());
		R.setSubCategorys(new ArrayList<SubCategory>());
		R.setDmodelMeasureMaps(new ArrayList<DmodelMeasureMap>());
		R.setDmodelRatios(new ArrayList<DmodelRatio>());
		return R;
	}

}
