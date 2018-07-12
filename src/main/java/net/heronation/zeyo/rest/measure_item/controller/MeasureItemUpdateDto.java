package net.heronation.zeyo.rest.measure_item.controller;

import org.joda.time.DateTime;

import lombok.Data;

@Data
public class MeasureItemUpdateDto {

	private Long id;
	
	private String name;

	private String metaDesc;

 
}