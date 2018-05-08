package net.heronation.zeyo.rest.repository.warranty;
  

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
import com.querydsl.core.types.dsl.StringPath;

import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.member.Member;

 
@RepositoryRestResource(collectionResourceRel = "warrantys", path = "warrantys")
public interface WarrantyRepository extends JpaRepository<Warranty, Long> , QueryDslPredicateExecutor<Warranty>{
    /****

  @RestResource(path = "names", rel = "names",exported = false)
  List<Person> findByName(String name);

***/

 	default void customize(QuerydslBindings bindings, QWarranty warranty) {

 
	}

 	
	@Override
	@RestResource(path = "", rel = "",exported = true)
	Page<Warranty> findAll(Pageable arg0);

 	
	@RestResource( rel = "findByScope", path = "findByScope")
	Madein findByScope(@Param("scope") String scope);
 	
}