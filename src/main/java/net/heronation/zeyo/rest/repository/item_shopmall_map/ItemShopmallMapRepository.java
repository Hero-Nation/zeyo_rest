package net.heronation.zeyo.rest.repository.item_shopmall_map;
  

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItem;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;

 
@RepositoryRestResource(collectionResourceRel = "item_shopmall_maps", path = "item_shopmall_maps")
@PreAuthorize("hasRole('ROLE_ADMIN')")

public interface ItemShopmallMapRepository extends JpaRepository<ItemShopmallMap, Long> , QueryDslPredicateExecutor<ItemShopmallMap>{
 

 	default void customize(QuerydslBindings bindings, QItemShopmallMap item_shopmall_map) {

 
	}
 	
	@Override
	@RestResource(path = "", rel = "",exported = false)
	Page<ItemShopmallMap> findAll(Pageable arg0);

 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	<S extends ItemShopmallMap> S save(S arg0);
 	
 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	<S extends ItemShopmallMap> List<S> save(Iterable<S> arg0);
 	
 	
 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	Iterable<ItemShopmallMap> findAll(Predicate arg0); 
 	
 	
 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	ItemShopmallMap findOne(Long arg0);
 	 	
}