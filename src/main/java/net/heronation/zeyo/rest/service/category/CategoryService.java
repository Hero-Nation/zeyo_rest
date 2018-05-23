package net.heronation.zeyo.rest.service.category;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItem;

public interface CategoryService {
	Page<Map<String,Object>> search(Predicate where, Pageable page);
	
	Page<Map<String,Object>> pure_search(Predicate where, Pageable page);

}