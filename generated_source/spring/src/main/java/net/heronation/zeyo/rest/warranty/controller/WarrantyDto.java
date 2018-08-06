package net.heronation.zeyo.rest.warranty.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.item.controller.Item;
import net.heronation.zeyo.rest.kindof.controller.Kindof;


@Data
public class WarrantyDto{
 
        private List<Item> items = new ArrayList<Item>();
 private Long id;




private Kindof kindof;




private String scope;



private DateTime  createDt;



private String useYn;
    
}