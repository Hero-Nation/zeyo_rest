package net.heronation.zeyo.rest.repository.madein;

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

@RepositoryRestResource(collectionResourceRel = "madeins", path = "madeins")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface MadeinRepository extends JpaRepository<Madein, Long>, QueryDslPredicateExecutor<Madein> {
 

	default void customize(QuerydslBindings bindings, QMadein madein) {
		//bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
	}

	@Override
	@RestResource(path = "", rel = "", exported = false)
	Page<Madein> findAll(Pageable arg0); 
	
	@RestResource(path = "findByName", rel = "findByName",exported = true)
	@Query("select m  from Madein m where m.name = ?1 and  m.useYn = 'Y'")
	List<Madein> findByName(@Param("name") String ktype);
 
	
	@RestResource(path = "distinct_name", rel = "distinct_name",exported = true)
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@Query("select distinct m from Madein m where m.useYn = 'Y'")
	List<Madein> distinct_name();
	
 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	Madein findOne(Long arg0);
 	
 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	<S extends Madein> S save(S arg0);
}