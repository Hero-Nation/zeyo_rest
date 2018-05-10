package net.heronation.zeyo.rest.service.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItem;

public interface CategoryService {
	Page<Category> search(Predicate where, Pageable page);

}