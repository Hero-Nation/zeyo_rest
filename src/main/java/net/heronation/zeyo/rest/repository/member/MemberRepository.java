package net.heronation.zeyo.rest.repository.member;
  

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

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;

 
@RepositoryRestResource(collectionResourceRel = "members", path = "members") 

public interface MemberRepository extends JpaRepository<Member, Long> , QueryDslPredicateExecutor<Member>{
 

 	default void customize(QuerydslBindings bindings, QMember member) {

 
	}
 	
 	@RestResource(path = "findByMemberId", rel = "findByMemberId",exported = true)
 	Member findByMemberId(@Param(value = "member_id") String member_id);
 
	@Override
	@RestResource(path = "", rel = "",exported = false)
	Page<Member> findAll(Pageable arg0);

	 
 	
 	
}