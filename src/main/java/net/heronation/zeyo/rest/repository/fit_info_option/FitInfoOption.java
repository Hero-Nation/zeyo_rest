package net.heronation.zeyo.rest.repository.fit_info_option;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.RequiredArgsConstructor;
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

	@JsonManagedReference
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