package net.heronation.zeyo.rest.repository.item_shopmall_map;
  

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

 
@RepositoryRestResource(collectionResourceRel = "item_shopmall_maps", path = "item_shopmall_maps")
//@PreAuthorize("hasRole('ROLE_CLIENT')")

public interface ItemShopmallMapRepository extends JpaRepository<ItemShopmallMap, Long> , QueryDslPredicateExecutor<ItemShopmallMap>{
 

 	default void customize(QuerydslBindings bindings, QItemShopmallMap item_shopmall_map) {

 
	}
 	
	@Override
	@RestResource(path = "", rel = "",exported = false)
	Page<ItemShopmallMap> findAll(Pageable arg0);


}