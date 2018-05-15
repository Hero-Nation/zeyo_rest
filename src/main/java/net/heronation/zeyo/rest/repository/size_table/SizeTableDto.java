package net.heronation.zeyo.rest.repository.size_table;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.item.Item;
import org.joda.time.DateTime;

@Value
public class SizeTableDto{
 
        private Long id;




private Item item;




private String visibleNameYn;



private String visibleCodeYn;



private String visibleBasicYn;



private String visibleItemImageYn;



private String visibleColorYn;



private String visibleMeasureTableYn;



private String visibleLaundryInfoYn;



private String visibleMeasureHowAYn;



private String visibleMeasureHowBYn;



private String visibleFitInfoYn;



 
private DateTime createDt;



private String useYn;
    
}