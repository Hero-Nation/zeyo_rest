package net.heronation.zeyo.rest.v2_rule.controller;

import org.joda.time.DateTime;

import lombok.Data;

@Data
public class V2RuleDto {

	private Long id;

	private String title;

	private String ruleType;

	private String ruleMessage;

	private Long firstCt;

	private String firstIncludeChild;

	private Long secondCt;

	private String secondIncludeChild;

	private DateTime createDt;

	private String useYn;

}