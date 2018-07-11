package net.heronation.zeyo.rest.item_drymethod_map.repository;
  

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.querydsl.core.types.Predicate;

 
@RepositoryRestResource(collectionResourceRel = "item_drymethod_maps", path = "item_drymethod_maps")
@PreAuthorize("hasRole('ROLE_ADMIN')")

public interface ItemDrymethodMapRepository extends JpaRepository<ItemDrymethodMap, Long> , QueryDslPredicateExecutor<ItemDrymethodMap>{
 
 	default void customize(QuerydslBindings bindings, QItemDrymethodMap item_drymethod_map) {

 
	}

	@Override
	@RestResource(path = "", rel = "",exported = false)
	Page<ItemDrymethodMap> findAll(Pageable arg0);

	
	@Override
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	Iterable<ItemDrymethodMap> findAll(Predicate arg0);
	
	
 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	<S extends ItemDrymethodMap> S save(S arg0);
 	
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	ItemDrymethodMap findOne(Long arg0);
}