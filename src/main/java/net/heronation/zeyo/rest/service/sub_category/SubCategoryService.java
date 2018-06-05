package net.heronation.zeyo.rest.service.sub_category;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;

public interface SubCategoryService {
//	Page<SubCategory> search(Predicate where, Pageable page);
	Page<Map<String,Object>> subsearch(Predicate where, Pageable page);

	Map<String,Object> distinct_name(String cate);

	
	
	
}