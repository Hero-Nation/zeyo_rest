package net.heronation.zeyo.rest.repository.material;

import java.util.List;

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

import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.warranty.Warranty;

@RepositoryRestResource(collectionResourceRel = "materials", path = "materials")
@PreAuthorize("hasRole('ROLE_ADMIN')")

public interface MaterialRepository extends JpaRepository<Material, Long>, QueryDslPredicateExecutor<Material> {
 
	default void customize(QuerydslBindings bindings, QMaterial material) {

	}

	@Override
	@RestResource(path = "", rel = "", exported = true)
	Page<Material> findAll(Pageable arg0); 
	
	@RestResource(path = "findByName", rel = "findByName",exported = true)
	@Query("select m  from Material m where m.name = ?1 and  m.useYn = 'Y'")
	List<Material> findByName(@Param("name") String ktype);
	
	
	@RestResource(path = "distinct_name", rel = "distinct_name",exported = true)
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@Query("select distinct m from Material m where m.useYn = 'Y'")
	List<Material> distinct_name();

}