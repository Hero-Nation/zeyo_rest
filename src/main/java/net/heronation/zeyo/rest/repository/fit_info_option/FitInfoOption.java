package net.heronation.zeyo.rest.repository.fit_info_option;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.heronation.zeyo.rest.repository.fit_info.FitInfo; 

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "FIT_INFO_OPTION")
@TableGenerator(name = "FIT_INFO_OPTION_ID_GENERATOR", table = "JPA_ID_TABLE", pkColumnValue = "FIT_INFO_OPTION_ID", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)

public class FitInfoOption {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FIT_INFO_OPTION_ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FIT_INFO_ID")
	private FitInfo fitInfo;
	private String name;

	private String useYn;
 
	@Override
	public String toString() {
		return "FitInfoOption ]";
	}
	
 


}