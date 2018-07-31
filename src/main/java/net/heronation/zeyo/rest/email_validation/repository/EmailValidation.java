package net.heronation.zeyo.rest.email_validation.repository;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.heronation.zeyo.rest.dmodel.repository.Dmodel;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailValidation other = (EmailValidation) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	@Transient
	private UUID hash_id = UUID.randomUUID();

	@Override
	public int hashCode() {
		return hash_id.hashCode();
	}
}