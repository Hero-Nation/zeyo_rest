package net.heronation.zeyo.rest.repository.bbs;
  

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.querydsl.core.types.dsl.StringPath;

import net.heronation.zeyo.rest.repository.member.Member;

 
@RepositoryRestResource(collectionResourceRel = "bbss", path = "bbss")
//@PreAuthorize("hasRole('ROLE_CLIENT')")

public interface BbsRepository extends JpaRepository<Bbs, Long> , QueryDslPredicateExecutor<Bbs>{
    /****

  @RestResource(path = "names", rel = "names",exported = false)
  List<Person> findByName(String name);

***/

 	default void customize(QuerydslBindings bindings, QBbs bbs) {

 
	}

 	
	@Override
	@RestResource(path = "", rel = "",exported = false)
	Page<Bbs> findAll(Pageable arg0);

}