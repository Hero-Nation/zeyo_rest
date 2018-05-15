package net.heronation.zeyo.rest.service.sub_category;
 
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
import net.heronation.zeyo.rest.repository.category.QCategory;
import net.heronation.zeyo.rest.repository.measure_item.QMeasureItem;
import net.heronation.zeyo.rest.repository.sub_category.QSubCategory;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;
import net.heronation.zeyo.rest.repository.sub_category.SubCategoryRepository;
import net.heronation.zeyo.rest.repository.sub_category_fit_info_map.QSubCategoryFitInfoMap;
import net.heronation.zeyo.rest.repository.sub_category_measure_map.QSubCategoryMeasureMap;



@Slf4j
@Service
@Transactional
public class SubCategoryServiceImpl implements SubCategoryService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private SubCategoryRepository sub_categoryRepository;



	@Autowired
	EntityManager entityManager;
	
	@Override
	public Page<SubCategory> search(Predicate where, Pageable page) {
 
		JPAQuery<SubCategory> query = new JPAQuery<SubCategory>(entityManager);
		 
		QSubCategory target = QSubCategory.subCategory;  
		
		QueryResults<SubCategory> R = query.from(target) 
				.leftJoin(target.subCategoryMeasureMaps)
				.leftJoin(target.subCategoryFitInfoMaps)
				.where(where)
				//.orderBy(target.id.desc())
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize()).fetchResults();
				
 
		return new PageImpl<SubCategory>(R.getResults(), page, R.getTotal());
		
	}


	@Override
	public Page<SubCategory> subsearch(Predicate where, Pageable page) {
 
		JPAQuery<SubCategory> query = new JPAQuery<SubCategory>(entityManager);
		 
		QSubCategory target = QSubCategory.subCategory;  
		
		QueryResults<SubCategory> R = query.from(target) 
				.leftJoin(target.subCategoryMeasureMaps)
				.leftJoin(target.subCategoryFitInfoMaps)
				.where(where)
				//.orderBy(target.id.desc())
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize()).fetchResults();
				
 
		return new PageImpl<SubCategory>(R.getResults(), page, R.getTotal());
		
	}


}