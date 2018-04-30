package net.heronation.zeyo.rest.repository.fit_into_choice;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.fit_info.FitInfo;import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOption;


@Value
public class FitIntoChoiceDto{
 
        private Long id;




private FitInfo fitInfo;




private FitInfoOption fitInfoOption;
    
}