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

@RepositoryRestResource(collectionResourceRel = "madeins", path = "madeins")
// //@PreAuthorize("hasRole('ROLE_CLIENT')")
public interface MadeinRepository extends JpaRepository<Madein, Long>, QueryDslPredicateExecutor<Madein> {
	/****
	 * 
	 * @RestResource(path = "names", rel = "names",exported = false) List<Person>
	 *                    findByName(String name);
	 * 
	 ***/

	default void customize(QuerydslBindings bindings, QMadein madein) {
		//bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
	}

	@Override
	@RestResource(path = "", rel = "", exported = false)
	Page<Madein> findAll(Pageable arg0);

	@RestResource(rel = "findByName", path = "findByName")
	Madein findByName(@Param("name") String name);
	
	
	@RestResource(path = "distinct_name", rel = "distinct_name",exported = true)
	@Query("select distinct m from Madein m where m.useYn = 'Y'")
	List<Madein> distinct_name();
    
}