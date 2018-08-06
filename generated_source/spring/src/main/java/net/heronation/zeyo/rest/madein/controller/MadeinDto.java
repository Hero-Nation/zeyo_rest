package net.heronation.zeyo.rest.madein.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.item.controller.Item;
import net.heronation.zeyo.rest.kindof.controller.Kindof;


@Data
public class MadeinDto{
 
        private List<Item> items = new ArrayList<Item>();
 private Long id;




private Kindof kindof;




private String name;



private String createDt;



private String useYn;
    
}