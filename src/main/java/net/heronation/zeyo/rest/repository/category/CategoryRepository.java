package net.heronation.zeyo.rest.repository.category;
  

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.security.access.prepost.PreAuthorize;

import com.querydsl.core.types.dsl.StringPath;

import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;

 
@RepositoryRestResource(collectionResourceRel = "categorys", path = "categorys")
////@PreAuthorize("hasRole('ROLE_CLIENT')")

public interface CategoryRepository extends JpaRepository<Category, Long> , QueryDslPredicateExecutor<Category>{
    /****

  @RestResource(path = "names", rel = "names",exported = false)
  List<Person> findByName(String name);

***/

 	default void customize(QuerydslBindings bindings, QCategory category) {

 
	}

 	
	@Override
	@RestResource(path = "", rel = "",exported = true)
	Page<Category> findAll(Pageable arg0);

 
	@RestResource(path = "distinct_name", rel = "distinct_name",exported = true)
 
	@Query("select distinct m from Category m where m.useYn = 'Y'")
	List<Category> distinct_name();
	
	
	
}