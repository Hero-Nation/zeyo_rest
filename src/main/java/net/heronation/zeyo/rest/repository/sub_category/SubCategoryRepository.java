package net.heronation.zeyo.rest.repository.sub_category;
  

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.querydsl.core.types.dsl.StringPath;

import net.heronation.zeyo.rest.repository.member.Member;

 
@RepositoryRestResource(collectionResourceRel = "sub_categorys", path = "sub_categorys")
//@PreAuthorize("hasRole('ROLE_CLIENT')")

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> , QueryDslPredicateExecutor<SubCategory>{
    /****

  @RestResource(path = "names", rel = "names",exported = false)
  List<Person> findByName(String name);

***/

 	default void customize(QuerydslBindings bindings, QSubCategory sub_category) {

 
	}
 	
 	
 	SubCategory findByCategoryId(@Param(value = "category_id") Long category_id);


	@Override
	@RestResource(path = "", rel = "",exported = true)
	Page<SubCategory> findAll(Pageable arg0);
	
	
	

}