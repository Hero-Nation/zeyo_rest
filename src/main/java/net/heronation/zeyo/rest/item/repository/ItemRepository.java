package net.heronation.zeyo.rest.item.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.querydsl.core.types.Predicate;

@RepositoryRestResource(collectionResourceRel = "items", path = "items")
@PreAuthorize("hasRole('ROLE_ADMIN')")

public interface ItemRepository extends JpaRepository<Item, Long>, QueryDslPredicateExecutor<Item> {
 

	default void customize(QuerydslBindings bindings, QItem item) {

	}

//	@Override
//	@PreAuthorize("hasRole('ROLE_ADMIN') or @AppSecurityEL.isMyItem(authentication,#item_id)")
//	Item findOne(@Param("item_id") Long item_id);

	// @Override
	// @RestResource(path = "", rel = "",exported = false)
	// Page<Item> findAll(Pageable arg0);

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') or @AppSecurityEL.isMyItem(authentication,#item_id)")
	void delete(@Param("item_id") Long arg0);

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
	<S extends Item> S save(S arg0);
	
	@Override
	@PreAuthorize("permitAll()")
	Item findOne(Long arg0);

	
	@Override
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	Iterable<Item> findAll(Predicate arg0, Sort arg1);
 
}