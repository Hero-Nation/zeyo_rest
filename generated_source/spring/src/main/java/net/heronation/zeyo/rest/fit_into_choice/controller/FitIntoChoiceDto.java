package net.heronation.zeyo.rest.fit_into_choice.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.fit_info.controller.FitInfo;import net.heronation.zeyo.rest.fit_info_option.controller.FitInfoOption;


@Data
public class FitIntoChoiceDto{
 
        private Long id;




private FitInfo fitInfo;




private FitInfoOption fitInfoOption;
    
}