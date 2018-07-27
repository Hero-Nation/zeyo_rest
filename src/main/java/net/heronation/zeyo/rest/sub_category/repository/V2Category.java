package net.heronation.zeyo.rest.sub_category.repository;

import java.util.List;

import lombok.Data;
import net.heronation.zeyo.rest.common.dto.IdNameDto;

@Data
public class V2Category {

	private String name;
	private List<IdNameDto> parent;
	private List<IdNameDto> child;
	private SubCategory info;
}
