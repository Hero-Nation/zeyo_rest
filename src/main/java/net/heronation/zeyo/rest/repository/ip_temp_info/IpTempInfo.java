package net.heronation.zeyo.rest.repository.ip_temp_info;

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
@Table(name = "IP_TEMP_INFO")
@TableGenerator(name = "IP_TEMP_INFO_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "IP_TEMP_INFO_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)
public class IpTempInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "IP_TEMP_INFO_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;
	private String ip;

	private String shopType;

	private String shopId;

	private String productId;

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
		IpTempInfo other = (IpTempInfo) obj;
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