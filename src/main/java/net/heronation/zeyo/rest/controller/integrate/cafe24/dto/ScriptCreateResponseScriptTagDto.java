package net.heronation.zeyo.rest.controller.integrate.cafe24.dto;

import org.joda.time.DateTime;

import lombok.Data;

@Data
public class ScriptCreateResponseScriptTagDto {
	private int shop_no;
	private String scdript_no;
	private String client_id;
	private String src;
	private String[] display_location;
	private int[] skin_no;

	private DateTime created_date;
	private DateTime updated_date;
}