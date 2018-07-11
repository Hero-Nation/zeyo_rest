package net.heronation.zeyo.rest.cloth_color.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import net.heronation.zeyo.rest.cloth_color.repository.ClothColorDto;
import net.heronation.zeyo.rest.common.dto.LIdDto;

public interface ClothColorService {
	Map<String,Object>  search(Map<String,Object> where,Pageable page); 
	

	
	String insert(ClothColorDto param);

	String update(ClothColorDto param);

	String delete(List<LIdDto> param);
}