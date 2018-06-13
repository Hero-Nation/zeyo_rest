package net.heronation.zeyo.rest.service.cloth_color;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.common.value.LIdVO;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColor;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColorDto;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;
import net.heronation.zeyo.rest.repository.size_option.SizeOptionDto;

public interface ClothColorService {
	Map<String,Object>  search(Map<String,Object> where,Pageable page); 
	
	
	String insert(ClothColorDto param);

	String update(ClothColorDto param);

	String delete(List<LIdVO> param);
}