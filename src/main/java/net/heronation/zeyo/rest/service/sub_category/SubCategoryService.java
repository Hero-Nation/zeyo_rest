package net.heronation.zeyo.rest.service.sub_category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;

public interface SubCategoryService {
	Page<SubCategory> search(Predicate where, Pageable page);
	Page<SubCategory> subsearch(Predicate where, Pageable page);

}