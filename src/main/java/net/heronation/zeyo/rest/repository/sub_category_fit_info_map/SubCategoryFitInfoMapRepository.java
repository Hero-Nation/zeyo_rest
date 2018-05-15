package net.heronation.zeyo.rest.repository.sub_category_fit_info_map;
  

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

 
@RepositoryRestResource(collectionResourceRel = "sub_category_fit_info_maps", path = "sub_category_fit_info_maps")
//@PreAuthorize("hasRole('ROLE_CLIENT')")

public interface SubCategoryFitInfoMapRepository extends JpaRepository<SubCategoryFitInfoMap, Long> , QueryDslPredicateExecutor<SubCategoryFitInfoMap>{
    /****

  @RestResource(path = "names", rel = "names",exported = false)
  List<Person> findByName(String name);

***/

 	default void customize(QuerydslBindings bindings, QSubCategoryFitInfoMap sub_category_fit_info_map) {

 
	}

 	
	@Override
	@RestResource(path = "", rel = "",exported = false)
	Page<SubCategoryFitInfoMap> findAll(Pageable arg0);

}