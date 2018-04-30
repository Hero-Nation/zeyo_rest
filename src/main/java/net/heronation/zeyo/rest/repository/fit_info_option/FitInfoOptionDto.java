package net.heronation.zeyo.rest.repository.fit_info_option;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.fit_info.FitInfo;import net.heronation.zeyo.rest.repository.fit_into_choice.FitIntoChoice;


@Value
public class FitInfoOptionDto{
 
        private Long id;




private FitInfo fitInfo;




private String name;



private String useYn;



private List<FitIntoChoice> fitIntoChoices = new ArrayList<FitIntoChoice>();
    
}