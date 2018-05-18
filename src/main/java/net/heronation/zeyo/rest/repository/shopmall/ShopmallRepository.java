package net.heronation.zeyo.rest.repository.shopmall;
  

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
import org.springframework.security.access.prepost.PreAuthorize;

import com.querydsl.core.types.dsl.StringPath;

import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.member.Member;

 
@RepositoryRestResource(collectionResourceRel = "shopmalls", path = "shopmalls")
////@PreAuthorize("hasRole('ROLE_CLIENT')")

public interface ShopmallRepository extends JpaRepository<Shopmall, Long> , QueryDslPredicateExecutor<Shopmall>{
    /****

  @RestResource(path = "names", rel = "names",exported = false)
  List<Person> findByName(String name);

***/

 	default void customize(QuerydslBindings bindings, QShopmall shopmall) {

 
	}

 	
	@Override
	@RestResource(path = "", rel = "",exported = false)
	Page<Shopmall> findAll(Pageable arg0);

	@RestResource(path = "distinct_name", rel = "distinct_name",exported = true)
	@Query("select distinct m from Shopmall m where m.useYn = 'Y'")
	List<Shopmall> distinct_name();
	
	
	@Override
	@RestResource(path = "", rel = "",exported = true)
	<S extends Shopmall> S save(S arg0);
}