package net.heronation.zeyo.rest.service.cloth_color;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import net.heronation.zeyo.rest.common.value.LIdVO;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColorDto;

public interface ClothColorService {
	Map<String,Object>  search(Map<String,Object> where,Pageable page); 
	

	
	String insert(ClothColorDto param);

	String update(ClothColorDto param);

	String delete(List<LIdVO> param);
}