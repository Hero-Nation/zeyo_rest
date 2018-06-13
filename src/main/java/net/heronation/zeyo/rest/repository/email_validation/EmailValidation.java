package net.heronation.zeyo.rest.repository.email_validation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "EMAIL_VALIDATION")
@TableGenerator(name = "EMAIL_VALIDATION_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "EMAIL_VALIDATION_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)
public class EmailValidation {

 
	@Id
	@Column(name = "email")
	private String email;

	private String otp;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;

}