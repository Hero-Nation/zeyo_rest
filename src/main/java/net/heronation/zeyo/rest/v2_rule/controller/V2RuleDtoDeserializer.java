package net.heronation.zeyo.rest.v2_rule.controller;

import java.io.IOException;

import org.joda.time.DateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.sub_category.repository.SubCategory;

@Slf4j
public class V2RuleDtoDeserializer extends JsonDeserializer<V2RuleDto> {

 

	@Override
	public V2RuleDto deserialize(JsonParser jsonParser, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		
		log.debug("V2RuleDtoDeserializer : deserialize");  
 

		JsonNode root_node = jsonParser.getCodec().readTree(jsonParser);

		Long V2Rule_id = root_node.get("id").asLong();
		
		if(V2Rule_id == null) {
			V2Rule_id = -1L;
		}
		
		Long first_ct_id = root_node.get("first_ct").asLong();
		
		SubCategory first_ct = new SubCategory();
		first_ct.setId(first_ct_id);
		
		if(first_ct_id.compareTo(0L) == 0) {
			first_ct = null;
		} 

		String first_include_child = root_node.get("first_include_child").asText();

		String rule_message = root_node.get("rule_message").asText();

		String rule_type = root_node.get("rule_type").asText(); 
		
		Long second_ct_id = root_node.get("second_ct").asLong();
		
		SubCategory second_ct = new SubCategory();
		second_ct.setId(second_ct_id);
		
		if(second_ct_id.compareTo(0L) == 0) {
			second_ct = null;
		} 
		
		String second_include_child = root_node.get("second_include_child").asText();
		 
		
		
		V2RuleDto R = new V2RuleDto();
		R.setId(V2Rule_id); 
		R.setFirstCt(first_ct);
		R.setFirstIncludeChild(first_include_child);
		R.setRuleMessage(rule_message);
		R.setRuleType(rule_type);
		R.setSecondCt(second_ct);
		R.setSecondIncludeChild(second_include_child);
		R.setUseYn("Y");
		R.setCreateDt(new DateTime());  
		return R;
	}

}
