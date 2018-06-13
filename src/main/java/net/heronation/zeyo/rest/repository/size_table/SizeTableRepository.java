package net.heronation.zeyo.rest.repository.size_table;

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

@RepositoryRestResource(collectionResourceRel = "size_tables", path = "size_tables")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface SizeTableRepository extends JpaRepository<SizeTable, Long>, QueryDslPredicateExecutor<SizeTable> {
 

	default void customize(QuerydslBindings bindings, QSizeTable size_table) {

	}

	@Override
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	<S extends SizeTable> S save(S arg0);
	// @Override
	// @RestResource(path = "", rel = "", exported = false)
	// Page<CompanyNoHistory> findAll(Pageable arg0);

}