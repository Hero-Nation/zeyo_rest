package net.heronation.zeyo.rest.repository.brand;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.Value;

import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.member.Member;

@Data
public class BrandDto {

	private List<Item> items = new ArrayList<Item>();
	private Long id;

	private Member member;

	private String name;

	private String useYn;
	
	private String link;

}