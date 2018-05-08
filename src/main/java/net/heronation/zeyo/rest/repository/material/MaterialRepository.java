package net.heronation.zeyo.rest.repository.material;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.querydsl.core.types.dsl.StringPath;

import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.member.Member;

@RepositoryRestResource(collectionResourceRel = "materials", path = "materials")
@PreAuthorize("hasRole('ROLE_CLIENT')")

public interface MaterialRepository extends JpaRepository<Material, Long>, QueryDslPredicateExecutor<Material> {
	/****
	 * 
	 * @RestResource(path = "names", rel = "names",exported = false) List<Person>
	 *                    findByName(String name);
	 * 
	 ***/

	default void customize(QuerydslBindings bindings, QMaterial material) {

	}

	@Override
	@RestResource(path = "", rel = "", exported = true)
	Page<Material> findAll(Pageable arg0);
	
	@RestResource( rel = "findByName", path = "findByName")
	Madein findByName(@Param("name") String name);

}