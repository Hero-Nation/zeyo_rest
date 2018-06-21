package net.heronation.zeyo.rest.controller.integrate.cafe24.dto;

import org.joda.time.DateTime;

import lombok.Data;
import net.heronation.zeyo.rest.repository.item.ItemRepository;
import net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMapRepository;
import net.heronation.zeyo.rest.repository.member.MemberRepository;
import net.heronation.zeyo.rest.repository.shopmall.ShopmallRepository;

@Data
public class Variants {
	private Variant[] variants;
}
