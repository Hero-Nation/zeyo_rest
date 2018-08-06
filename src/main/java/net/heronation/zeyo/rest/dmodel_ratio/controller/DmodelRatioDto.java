package net.heronation.zeyo.rest.dmodel_ratio.controller;

import lombok.Data;
import net.heronation.zeyo.rest.dmodel.repository.Dmodel;
 

@Data
public class DmodelRatioDto {

	private Long id;

	private Dmodel dmodel;

	private String defaultYn;

	private String minValue;

	private String maxValue;

	private String ratioValue;

	private String useYn;

}