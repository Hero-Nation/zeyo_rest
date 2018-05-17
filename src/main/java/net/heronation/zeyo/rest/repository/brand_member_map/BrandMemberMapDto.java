package net.heronation.zeyo.rest.repository.brand_member_map;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.brand.Brand;import net.heronation.zeyo.rest.repository.member.Member;


@Value
public class BrandMemberMapDto{
 
        private Long id;




private Brand brand;




private Member member;




private String linkYn;



private String useYn;
    
}