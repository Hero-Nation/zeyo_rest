package net.heronation.zeyo.rest.repository.sub_category;
  

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.querydsl.core.types.dsl.StringPath;

import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;

 
@RepositoryRestResource(collectionResourceRel = "sub_categorys", path = "sub_categorys")
////@PreAuthorize("hasRole('ROLE_CLIENT')")

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> , QueryDslPredicateExecutor<SubCategory>{
 
 	default void customize(QuerydslBindings bindings, QSubCategory sub_category) {

 
	}
 	 

	@Override
	@RestResource(path = "", rel = "",exported = true)
	Page<SubCategory> findAll(Pageable arg0);
	
 
	
	@RestResource(path = "distinct_name", rel = "distinct_name",exported = true)
	@Query("select distinct m  from SubCategory m where  m.category = ?1 and m.useYn = 'Y'")
	List<SubCategory> distinct_name(@Param(value = "category") Category category);
	
	
	@RestResource(path = "findByName", rel = "findByName",exported = true)
	@Query("select m from SubCategory m where m.name = ?1 and  m.useYn = 'Y'")
	List<SubCategory> findByName(@Param("name") String ktype);

}