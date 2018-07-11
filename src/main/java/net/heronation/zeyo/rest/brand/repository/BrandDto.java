package net.heronation.zeyo.rest.brand.repository;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import net.heronation.zeyo.rest.item.repository.Item;
import net.heronation.zeyo.rest.member.repository.Member;

@Data
public class BrandDto {

	private List<Item> items = new ArrayList<Item>();
	private Long id;

	private Member member;

	private String name;

	private String useYn;
	
	private String link;

}