package net.heronation.zeyo.rest.repository.item_ironing_map;
  

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

 
@RepositoryRestResource(collectionResourceRel = "item_ironing_maps", path = "item_ironing_maps")
//@PreAuthorize("hasRole('ROLE_CLIENT')")

public interface ItemIroningMapRepository extends JpaRepository<ItemIroningMap, Long> , QueryDslPredicateExecutor<ItemIroningMap>{
    /****

  @RestResource(path = "names", rel = "names",exported = false)
  List<Person> findByName(String name);

***/

 	default void customize(QuerydslBindings bindings, QItemIroningMap item_ironing_map) {

 
	}
 	
	@Override
	@RestResource(path = "", rel = "",exported = false)
	Page<ItemIroningMap> findAll(Pageable arg0);


}