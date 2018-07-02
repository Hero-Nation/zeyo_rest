package net.heronation.zeyo.rest.repository.ip_temp_info;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

}