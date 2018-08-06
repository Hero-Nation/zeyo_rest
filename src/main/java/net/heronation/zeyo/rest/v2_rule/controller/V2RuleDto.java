package net.heronation.zeyo.rest.v2_rule.controller;

import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.heronation.zeyo.rest.sub_category.repository.SubCategory;
import net.heronation.zeyo.rest.v2_rule.repository.V2Rule;

@Data
@AllArgsConstructor
@JsonDeserialize(using = V2RuleDtoDeserializer.class)
public class V2RuleDto {

	private Long id;
 
	private String ruleType;
 
	private String ruleMessage;

	private SubCategory firstCt;

	private String firstIncludeChild;

	private SubCategory secondCt;

	private String secondIncludeChild;

	private DateTime createDt;

	private String useYn;
	public V2Rule getAsEntity() {
		
		V2Rule R = new V2Rule();
		R.setId(id); 
		R.setCreateDt(createDt);  
		R.setUseYn(useYn);
		R.setRuleMessage(ruleMessage);
		R.setRuleType(ruleType);
		R.setFirstCt(getFirstCt());
		R.setFirstIncludeChild(firstIncludeChild);
		R.setSecondCt(getSecondCt());
		R.setSecondIncludeChild(secondIncludeChild);
		
		return R;
		
	}
	public V2RuleDto() {
		// TODO Auto-generated constructor stub
	}
}