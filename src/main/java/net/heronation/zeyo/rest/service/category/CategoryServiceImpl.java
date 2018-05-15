package net.heronation.zeyo.rest.service.category;
 
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.category.CategoryRepository;
import net.heronation.zeyo.rest.repository.category.QCategory;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItem;
import net.heronation.zeyo.rest.repository.measure_item.QMeasureItem;
import net.heronation.zeyo.rest.repository.sub_category.QSubCategory;



@Slf4j
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private CategoryRepository categoryRepository;


	@Autowired
	EntityManager entityManager;
	
	@Override
	public Page<Category> search(Predicate where, Pageable page) {

		JPAQuery<Category> query = new JPAQuery<Category>(entityManager);
 
		QCategory target = QCategory.category; 

		QueryResults<Category> R = query.from(target) 
				.where(where)
				//.orderBy(target.id.desc())
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize()).fetchResults();
				
 
		return new PageImpl<Category>(R.getResults(), page, R.getTotal());

	}
}