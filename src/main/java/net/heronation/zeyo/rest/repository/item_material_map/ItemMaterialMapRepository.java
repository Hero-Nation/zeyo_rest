package net.heronation.zeyo.rest.repository.item_material_map;
  

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.querydsl.core.types.Predicate;

 
@RepositoryRestResource(collectionResourceRel = "item_material_maps", path = "item_material_maps")
@PreAuthorize("hasRole('ROLE_ADMIN')")

public interface ItemMaterialMapRepository extends JpaRepository<ItemMaterialMap, Long> , QueryDslPredicateExecutor<ItemMaterialMap>{
 

 	default void customize(QuerydslBindings bindings, QItemMaterialMap item_material_map) {

 
	}

 	
	@Override
	@RestResource(path = "", rel = "",exported = false)
	Page<ItemMaterialMap> findAll(Pageable arg0);
	
	@Override
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	Iterable<ItemMaterialMap> findAll(Predicate arg0);
	
	
 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	<S extends ItemMaterialMap> S save(S arg0);

 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	<S extends ItemMaterialMap> List<S> save(Iterable<S> arg0);
}