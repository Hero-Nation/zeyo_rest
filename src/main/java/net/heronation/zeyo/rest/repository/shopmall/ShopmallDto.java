package net.heronation.zeyo.rest.repository.shopmall;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import lombok.Data;
import lombok.Value;
import net.heronation.zeyo.rest.repository.bbs.Bbs;
import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMap;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.MemberDto;

@Data
public class ShopmallDto {
 
	private Long id; 
	private String name; 
	private String link;

}