package net.heronation.zeyo.rest.brand.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.item.controller.Item;
import net.heronation.zeyo.rest.member.controller.Member;


@Data
public class BrandDto{
 
        private List<Item> items = new ArrayList<Item>();
 private Long id;




private Member member;




private String name;



private String useYn;
    
}