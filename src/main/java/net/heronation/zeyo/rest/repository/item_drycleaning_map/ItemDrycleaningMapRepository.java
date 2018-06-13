package net.heronation.zeyo.rest.repository.item_drycleaning_map;
  

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.querydsl.core.types.Predicate;

 
@RepositoryRestResource(collectionResourceRel = "item_drycleaning_maps", path = "item_drycleaning_maps")
@PreAuthorize("hasRole('ROLE_ADMIN')")

public interface ItemDrycleaningMapRepository extends JpaRepository<ItemDrycleaningMap, Long> , QueryDslPredicateExecutor<ItemDrycleaningMap>{
 
 	default void customize(QuerydslBindings bindings, QItemDrycleaningMap item_drycleaning_map) {

 
	}
 	
	@Override
	@RestResource(path = "", rel = "",exported = false)
	Page<ItemDrycleaningMap> findAll(Pageable arg0);

	@Override
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	Iterable<ItemDrycleaningMap> findAll(Predicate arg0);
	
 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	<S extends ItemDrycleaningMap> S save(S arg0);
}