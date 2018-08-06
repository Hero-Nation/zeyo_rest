package net.heronation.zeyo.rest.shopmall.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import net.heronation.zeyo.rest.item_shopmall_map.controller.ItemShopmallMap;
import net.heronation.zeyo.rest.member.controller.Member;


@Data
public class ShopmallDto{
 
        private List<ItemShopmallMap> itemShopmallMaps = new ArrayList<ItemShopmallMap>();
 private Long id;




private Member member;




private String name;



private String useYn;
    
}