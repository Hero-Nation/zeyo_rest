package net.heronation.zeyo.rest.item_ironing_map.repository;
  

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource; 
import com.querydsl.core.types.dsl.StringPath;

 
@RepositoryRestResource(collectionResourceRel = "item_ironing_maps", path = "item_ironing_maps")
public interface ItemIroningMapRepository extends JpaRepository<ItemIroningMap, Long> , QuerydslPredicateExecutor<ItemIroningMap>{
    /****

  @RestResource(path = "names", rel = "names",exported = false)
  List<Person> findByName(String name);

***/

 	default void customize(QuerydslBindings bindings, QItemIroningMap item_ironing_map) {

 
	}

}