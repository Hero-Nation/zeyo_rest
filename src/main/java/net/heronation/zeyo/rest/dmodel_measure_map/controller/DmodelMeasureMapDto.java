package net.heronation.zeyo.rest.dmodel_measure_map.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;
import net.heronation.zeyo.rest.dmodel.repository.Dmodel;
import net.heronation.zeyo.rest.measure_item.repository.MeasureItem;
 

@Data
public class DmodelMeasureMapDto {

	private Long id;

	private Dmodel dmodel;

	private MeasureItem measureItem;

	private String minValue;

	private String maxValue;

	private String useYn;

}