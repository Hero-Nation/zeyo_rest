package net.heronation.zeyo.rest.repository.shopmall_member_map;
  

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource; 
import com.querydsl.core.types.dsl.StringPath;

 
@RepositoryRestResource(collectionResourceRel = "shopmall_member_maps", path = "shopmall_member_maps")
public interface ShopmallMemberMapRepository extends JpaRepository<ShopmallMemberMap, Long> , QueryDslPredicateExecutor<ShopmallMemberMap>{
 

 	default void customize(QuerydslBindings bindings, QShopmallMemberMap shopmall_member_map) {

 
	}

//	@Override
//	@RestResource(path = "", rel = "", exported = false)
//	Page<CompanyNoHistory> findAll(Pageable arg0);

}