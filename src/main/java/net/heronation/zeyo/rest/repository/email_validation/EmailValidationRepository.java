package net.heronation.zeyo.rest.repository.email_validation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import com.querydsl.core.types.dsl.StringPath;

@RepositoryRestResource(collectionResourceRel = "email_validations", path = "email_validations")
public interface EmailValidationRepository
		extends JpaRepository<EmailValidation, Long>, QueryDslPredicateExecutor<EmailValidation> {

	default void customize(QuerydslBindings bindings, QEmailValidation email_validation) {

	}

}