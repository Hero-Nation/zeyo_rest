package net.heronation.zeyo.rest.repository.item;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;

import net.heronation.zeyo.rest.repository.member.Member;

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
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	Item findOne(Long arg0);

	
	@Override
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	Iterable<Item> findAll(Predicate arg0, Sort arg1);
 
}