package net.heronation.zeyo.rest.category.repository;
  

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

 
@RepositoryRestResource(collectionResourceRel = "categorys", path = "categorys")
@PreAuthorize("hasRole('ROLE_ADMIN')")

public interface CategoryRepository extends JpaRepository<Category, Long> , QueryDslPredicateExecutor<Category>{
 

 	default void customize(QuerydslBindings bindings, QCategory category) {

 
	}

 	
	@Override
	@RestResource(path = "", rel = "",exported = true)
	Page<Category> findAll(Pageable arg0);

 
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RestResource(path = "distinct_name", rel = "distinct_name",exported = true) 
	@Query("select distinct m from Category m where m.useYn = 'Y'")
	List<Category> distinct_name();
	
	@RestResource(path = "findByName", rel = "findByName",exported = true)
	@Query("select m from Category m where m.name = ?1 and  m.useYn = 'Y'")
	List<Category> findByName(@Param("name") String name);

	
 	@Override
 	//@PreAuthorize("hasRole('ROLE_CLIENT')")
 	@PreAuthorize("permitAll()")
 	Category findOne(Long arg0);
 	
 	
 	@Override
 	//@PreAuthorize("hasRole('ROLE_CLIENT')")
 	@PreAuthorize("permitAll()")
 	<S extends Category> S save(S arg0);
 	
}