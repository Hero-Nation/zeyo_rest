package net.heronation.zeyo.rest.repository.shopmall;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMap;
import net.heronation.zeyo.rest.repository.member.Member;


@Value
public class ShopmallDto{
 
        private List<ItemShopmallMap> itemShopmallMaps = new ArrayList<ItemShopmallMap>();
 private Long id;




private Member member;




private String name;



private String useYn;
    
}