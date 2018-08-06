package net.heronation.zeyo.rest.fit_info_option.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.fit_info.controller.FitInfo;import net.heronation.zeyo.rest.fit_into_choice.controller.FitIntoChoice;


@Data
public class FitInfoOptionDto{
 
        private Long id;




private FitInfo fitInfo;




private String name;



private String useYn;



private List<FitIntoChoice> fitIntoChoices = new ArrayList<FitIntoChoice>();
    
}