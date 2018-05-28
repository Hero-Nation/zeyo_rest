package net.heronation.zeyo.rest.repository.item_drymethod_map;
  

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

 
@RepositoryRestResource(collectionResourceRel = "item_drymethod_maps", path = "item_drymethod_maps")
@PreAuthorize("hasRole('ROLE_ADMIN')")

public interface ItemDrymethodMapRepository extends JpaRepository<ItemDrymethodMap, Long> , QueryDslPredicateExecutor<ItemDrymethodMap>{
 
 	default void customize(QuerydslBindings bindings, QItemDrymethodMap item_drymethod_map) {

 
	}

	@Override
	@RestResource(path = "", rel = "",exported = false)
	Page<ItemDrymethodMap> findAll(Pageable arg0);

}