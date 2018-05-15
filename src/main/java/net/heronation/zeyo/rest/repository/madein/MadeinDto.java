package net.heronation.zeyo.rest.repository.madein;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Value;

import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.kindof.Kindof;

import org.joda.time.DateTime;

@Value
public class MadeinDto {

	private List<Item> items = new ArrayList<Item>();
	private Long id;

	private Kindof kindof;

	private String name;

	private DateTime createDt;

	private String useYn;

}