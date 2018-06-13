package net.heronation.zeyo.rest.repository.item_cloth_color_map;
  

import java.util.List;

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

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;

import net.heronation.zeyo.rest.repository.item_bleach_map.ItemBleachMap;
import net.heronation.zeyo.rest.repository.member.Member;

 
@RepositoryRestResource(collectionResourceRel = "item_cloth_color_maps", path = "item_cloth_color_maps")
@PreAuthorize("hasRole('ROLE_ADMIN')")

public interface ItemClothColorMapRepository extends JpaRepository<ItemClothColorMap, Long> , QueryDslPredicateExecutor<ItemClothColorMap>{
 

 	default void customize(QuerydslBindings bindings, QItemClothColorMap item_cloth_color_map) {

 
	}

 	
 	
	@Override
	@RestResource(path = "", rel = "",exported = false)
	Page<ItemClothColorMap> findAll(Pageable arg0);
	
	@Override
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	Iterable<ItemClothColorMap> findAll(Predicate arg0);

	
 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	<S extends ItemClothColorMap> S save(S arg0);
 	
 	
 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	<S extends ItemClothColorMap> List<S> save(Iterable<S> arg0);
}