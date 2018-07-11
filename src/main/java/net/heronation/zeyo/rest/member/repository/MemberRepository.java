package net.heronation.zeyo.rest.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.querydsl.core.types.Predicate;

@RepositoryRestResource(collectionResourceRel = "members", path = "members")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface MemberRepository extends JpaRepository<Member, Long>, QueryDslPredicateExecutor<Member> {

	default void customize(QuerydslBindings bindings, QMember member) {

	}

	@RestResource(path = "findByMemberId", rel = "findByMemberId", exported = false)
	@PreAuthorize("permitAll()")
	Member findByMemberId(@Param(value = "member_id") String member_id);

	@Override
	//@PreAuthorize("permitAll()")
	@RestResource(path = "", rel = "", exported = false)
	Page<Member> findAll(Pageable arg0);

	
 	@Override 
 	@PreAuthorize("permitAll()")
 	<S extends Member> S save(S arg0);
 	
 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	Member getOne(Long arg0);
 	
 	
 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	Member findOne(Long arg0);
 	
 	@Override
 	@PreAuthorize("permitAll()")
 	Member findOne(Predicate arg0);
 	
}