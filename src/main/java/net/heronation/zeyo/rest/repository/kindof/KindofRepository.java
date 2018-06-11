package net.heronation.zeyo.rest.repository.kindof;
  

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

import net.heronation.zeyo.rest.repository.member.Member;

 
@RepositoryRestResource(collectionResourceRel = "kindofs", path = "kindofs")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface KindofRepository extends JpaRepository<Kindof, Long> , QueryDslPredicateExecutor<Kindof>{
 

 	default void customize(QuerydslBindings bindings, QKindof kindof) {

 
	}
 	
// 
//	@Override
//	@RestResource(path = "", rel = "",exported = false)
//	Page<Kindof> findAll(Pageable arg0);

 	@RestResource( rel = "check", path = "check")
 	@Query("select a from Kindof a where a.ktype = ?1 and a.kvalue = ?2 and a.useYn = ?3")
 	Kindof check(@Param("ktype") String ktype, @Param("kvalue") String kvalue, @Param("useYn") String useYn);
 	
 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	<S extends Kindof> S save(S arg0);
 	
 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	Kindof findOne(Long arg0);
 	
}