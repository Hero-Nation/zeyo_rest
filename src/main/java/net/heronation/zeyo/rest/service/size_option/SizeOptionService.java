package net.heronation.zeyo.rest.service.size_option;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;

public interface SizeOptionService {
	Page<Map<String, Object>> search(Predicate where,Pageable page); 
	
	Map<String, Object> category_count(Predicate where); 
	
}