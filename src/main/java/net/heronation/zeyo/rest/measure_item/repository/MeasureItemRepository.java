package net.heronation.zeyo.rest.measure_item.repository;
  

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

 
@RepositoryRestResource(collectionResourceRel = "measure_items", path = "measure_items")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface MeasureItemRepository extends JpaRepository<MeasureItem, Long> , QueryDslPredicateExecutor<MeasureItem>{
 
 	default void customize(QuerydslBindings bindings, QMeasureItem measure_item) {

 
	}

 	
	@Override
	@RestResource(path = "", rel = "",exported = true)
	Page<MeasureItem> findAll(Pageable arg0);

	 
	
	@RestResource(path = "findByName", rel = "findByName",exported = true)
	@Query("select m from MeasureItem m where m.name = ?1 and  m.useYn = 'Y'")
	List<MeasureItem> findByName(@Param("name") String ktype);
	
	
	@RestResource(path = "distinct_name", rel = "distinct_name",exported = true) 
	@Query("select distinct m from MeasureItem m where m.useYn = 'Y'")
	List<MeasureItem> distinct_name();
	
}