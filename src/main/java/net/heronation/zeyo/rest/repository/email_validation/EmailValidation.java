package net.heronation.zeyo.rest.repository.email_validation;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

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