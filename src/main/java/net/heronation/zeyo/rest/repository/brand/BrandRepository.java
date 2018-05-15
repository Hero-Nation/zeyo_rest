package net.heronation.zeyo.rest.repository.brand;
  

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

import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.kindof.Kindof;
import net.heronation.zeyo.rest.repository.member.Member;

 
@RepositoryRestResource(collectionResourceRel = "brands", path = "brands")
////@PreAuthorize("hasRole('ROLE_CLIENT')")

public interface BrandRepository extends JpaRepository<Brand, Long> , QueryDslPredicateExecutor<Brand>{
    /****

  @RestResource(path = "names", rel = "names",exported = false)
  List<Person> findByName(String name);

***/

 	default void customize(QuerydslBindings bindings, QBrand brand) {

 
	}

	@Override
	@RestResource(path = "", rel = "",exported = true)
	Page<Brand> findAll(Pageable arg0);

	@RestResource(path = "distinct_name", rel = "distinct_name",exported = true)
	@Query("select distinct(m.name) from Brand m where m.useYn = 'Y'")
	List<Brand> distinct_name();
 	
	@RestResource(path = "unique", rel = "unique",exported = true)
	@Query("select m.name from Brand m where m.name = ?1 and  m.useYn = 'Y'")
	List<Brand> unique(@Param("name") String ktype);
	 
 	 
	@Override
	@RestResource(path = "", rel = "",exported = false)
	<S extends Brand> S save(S arg0);
	
}