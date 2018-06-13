package net.heronation.zeyo.rest.repository.item_size_option_map;
  

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

 
@RepositoryRestResource(collectionResourceRel = "item_size_option_maps", path = "item_size_option_maps")
@PreAuthorize("hasRole('ROLE_ADMIN')")

public interface ItemSizeOptionMapRepository extends JpaRepository<ItemSizeOptionMap, Long> , QueryDslPredicateExecutor<ItemSizeOptionMap>{
 

 	default void customize(QuerydslBindings bindings, QItemSizeOptionMap item_size_option_map) {

 
	}

 	
	@Override
	@RestResource(path = "", rel = "",exported = false)
	Page<ItemSizeOptionMap> findAll(Pageable arg0);
	
	@Override
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	Iterable<ItemSizeOptionMap> findAll(Predicate arg0);
	

 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	<S extends ItemSizeOptionMap> S save(S arg0);

 	
 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	<S extends ItemSizeOptionMap> List<S> save(Iterable<S> arg0);
}