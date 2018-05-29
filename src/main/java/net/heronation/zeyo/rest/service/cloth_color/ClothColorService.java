package net.heronation.zeyo.rest.service.cloth_color;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.cloth_color.ClothColor;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;

public interface ClothColorService {
	Map<String,Object>  search(Map<String,Object> where,Pageable page); 
}