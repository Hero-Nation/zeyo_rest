package net.heronation.zeyo.rest.repository.warranty;
  

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

 
@RepositoryRestResource(collectionResourceRel = "warrantys", path = "warrantys")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface WarrantyRepository extends JpaRepository<Warranty, Long> , QueryDslPredicateExecutor<Warranty>{
 
 	default void customize(QuerydslBindings bindings, QWarranty warranty) {

 
	}

 	
	@Override
	@RestResource(path = "", rel = "",exported = true)
	Page<Warranty> findAll(Pageable arg0);

 	 
	
	@RestResource(path = "findByScope", rel = "findByScope",exported = true)
	@Query("select m  from Warranty m where m.scope = ?1 and  m.useYn = 'Y'")
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	List<Warranty> findByName(@Param("scope") String ktype);
	
	
	
	@RestResource(path = "distinct_name", rel = "distinct_name",exported = true)
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@Query("select distinct m from Warranty m where m.useYn = 'Y'")
	List<Warranty> distinct_name();
	
 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	Warranty findOne(Long arg0);
 	
 	
 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	<S extends Warranty> S save(S arg0);
}