package net.heronation.zeyo.rest.service.category;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.common.value.LIdVO;
import net.heronation.zeyo.rest.common.value.NameVO;
import net.heronation.zeyo.rest.common.value.ToggleVO;
import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItem;

public interface CategoryService {
	Map<String, Object> search(Map<String, Object> where, Pageable page);

	Map<String, Object> pure_search(Map<String, Object> where, Pageable page);

	String insert(NameVO param);
	
	String update(ToggleVO param);

	String delete(List<LIdVO> param);
}