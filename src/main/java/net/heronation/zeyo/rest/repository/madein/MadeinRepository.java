package net.heronation.zeyo.rest.repository.madein;
  

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

 
@RepositoryRestResource(collectionResourceRel = "madeins", path = "madeins")
//@PreAuthorize("hasRole('ROLE_CLIENT')")
public interface MadeinRepository extends JpaRepository<Madein, Long> , QueryDslPredicateExecutor<Madein>{
    /****

  @RestResource(path = "names", rel = "names",exported = false)
  List<Person> findByName(String name);

***/

 	default void customize(QuerydslBindings bindings, QMadein madein) {

 
	}
 	
	@Override
	@RestResource(path = "", rel = "",exported = true)
	Page<Madein> findAll(Pageable arg0);

	@RestResource( rel = "findByName", path = "findByName")
	Madein findByName(@Param("name") String name);

}