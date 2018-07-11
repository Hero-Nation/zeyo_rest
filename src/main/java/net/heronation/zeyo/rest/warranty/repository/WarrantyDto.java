package net.heronation.zeyo.rest.warranty.repository;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import lombok.Value;
import net.heronation.zeyo.rest.item.repository.Item;
import net.heronation.zeyo.rest.kindof.repository.Kindof;

@Value
public class WarrantyDto {

	private List<Item> items = new ArrayList<Item>();
	private Long id;

	private Kindof kindof;

	private String scope;

	private DateTime createDt;

	private String useYn;

}