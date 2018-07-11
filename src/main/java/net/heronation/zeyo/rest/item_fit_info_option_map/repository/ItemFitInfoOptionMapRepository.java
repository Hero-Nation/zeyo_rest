package net.heronation.zeyo.rest.item_fit_info_option_map.repository;
  

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.querydsl.core.types.Predicate;

 
@RepositoryRestResource(collectionResourceRel = "item_fit_info_option_maps", path = "item_fit_info_option_maps")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface ItemFitInfoOptionMapRepository extends JpaRepository<ItemFitInfoOptionMap, Long> , QueryDslPredicateExecutor<ItemFitInfoOptionMap>{
 
 	default void customize(QuerydslBindings bindings, QItemFitInfoOptionMap item_fit_info_option_map) {

 
	}

//	@Override
//	@RestResource(path = "", rel = "", exported = false)
//	Page<CompanyNoHistory> findAll(Pageable arg0);
 	
	@Override
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	Iterable<ItemFitInfoOptionMap> findAll(Predicate arg0);
	
	
	
 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	<S extends ItemFitInfoOptionMap> S save(S arg0);
 	
 	
 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	<S extends ItemFitInfoOptionMap> List<S> save(Iterable<S> arg0);
 	
 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	ItemFitInfoOptionMap findOne(Long arg0);
}