package net.heronation.zeyo.rest.service.sub_category;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import net.heronation.zeyo.rest.common.value.LIdDto;
import net.heronation.zeyo.rest.repository.sub_category.SubCategoryDto;

public interface SubCategoryService {
	// Page<SubCategory> search(Predicate where, Pageable page);
	Map<String, Object> subsearch(Map<String, Object> where, Pageable page);

	Map<String, Object> distinct_name(String cate);
	
	Map<String, Object> single_info(Long id);

	String insert(SubCategoryDto param);

	String update(SubCategoryDto param);

	String delete(List<LIdDto> param);

}