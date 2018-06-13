package net.heronation.zeyo.rest.repository.material;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import lombok.Value;
import net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMap;

@Value
public class MaterialDto {

	private List<ItemMaterialMap> itemMaterialMaps = new ArrayList<ItemMaterialMap>();
	private Long id;

	private String name;

	private String image;

	private String metaDesc;

	private DateTime createDt;

	private String useYn;

}