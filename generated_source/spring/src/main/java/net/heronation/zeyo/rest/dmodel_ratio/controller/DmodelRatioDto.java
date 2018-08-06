package net.heronation.zeyo.rest.dmodel_ratio.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.dmodel.controller.Dmodel;


@Data
public class DmodelRatioDto{
 
        private Long id;




private Dmodel dmodel;




private String default;



private String minValue;



private String maxValue;



private String ratioValue;



private String useYn;
    
}