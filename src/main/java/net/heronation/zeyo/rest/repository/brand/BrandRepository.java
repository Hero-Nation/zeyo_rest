package net.heronation.zeyo.rest.repository.brand;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.querydsl.core.types.dsl.StringPath;

import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.kindof.Kindof;
import net.heronation.zeyo.rest.repository.member.Member;

@RepositoryRestResource(collectionResourceRel = "brands", path = "brands")
@PreAuthorize("hasRole('ROLE_ADMIN')")

public interface BrandRepository extends JpaRepository<Brand, Long>, QueryDslPredicateExecutor<Brand> {

	default void customize(QuerydslBindings bindings, QBrand brand) {

	}

	@Override
	@RestResource(path = "", rel = "", exported = true)
	Page<Brand> findAll(Pageable arg0);

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RestResource(path = "distinct_name", rel = "distinct_name", exported = true)
	@Query("select distinct m from Brand m where m.useYn = 'Y'")
	List<Brand> distinct_name();

	@RestResource(path = "findByName", rel = "findByName", exported = true)
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@Query("select m from Brand m where m.name = ?1 and  m.useYn = 'Y'")
	List<Brand> findByName(@Param("name") String ktype);

	@Override
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	Brand findOne(Long arg0);

	@Override
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RestResource(path = "", rel = "", exported = true)
	<S extends Brand> S save(S arg0);

}