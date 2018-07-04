package net.heronation.zeyo.rest.repository.consumer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "CONSUMER")
@TableGenerator(name = "CONSUMER_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "CONSUMER_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)
public class Consumer {
 

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CONSUMER_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	private String corpType;

	private String corpId;

	private String oauthId;

	private String oauthCode;

	private String accessToken;

	private String refreshToken;

	private String now_ip;
	private String now_shop_type;
	private String now_shop_id;
	private String now_product_id;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createDt;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime lastAccessDt;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime deleteDt;

	private String useYn;
	
	@Override
	public String toString() {
		return "CONSUMER ]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consumer other = (Consumer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

}