package net.heronation.zeyo.rest.repository.sub_category_measure_map;
  

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.querydsl.core.types.dsl.StringPath;

import net.heronation.zeyo.rest.repository.member.Member;

 
@RepositoryRestResource(collectionResourceRel = "sub_category_measure_maps", path = "sub_category_measure_maps")
//@PreAuthorize("hasRole('ROLE_CLIENT')")

public interface SubCategoryMeasureMapRepository extends JpaRepository<SubCategoryMeasureMap, Long> , QueryDslPredicateExecutor<SubCategoryMeasureMap>{
 
 	default void customize(QuerydslBindings bindings, QSubCategoryMeasureMap sub_category_measure_map) {

 
	}

	@Override
	@RestResource(path = "", rel = "",exported = false)
	Page<SubCategoryMeasureMap> findAll(Pageable arg0);

}