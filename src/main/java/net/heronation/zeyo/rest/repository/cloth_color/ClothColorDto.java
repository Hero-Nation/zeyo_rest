package net.heronation.zeyo.rest.repository.cloth_color;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.Value;

import net.heronation.zeyo.rest.repository.item_cloth_color_map.ItemClothColorMap;
import net.heronation.zeyo.rest.repository.kindof.Kindof;

@Data
public class ClothColorDto {
 
	private Long id; 

	private String name; 

}