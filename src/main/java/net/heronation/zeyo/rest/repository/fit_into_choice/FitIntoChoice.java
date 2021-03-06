package net.heronation.zeyo.rest.repository.fit_into_choice;
 
import javax.persistence.*; 
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.heronation.zeyo.rest.repository.fit_info.FitInfo;import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOption;
 
@Entity 
@Data
@RequiredArgsConstructor
@Table(name="FIT_INTO_CHOICE")
@TableGenerator(name="FIT_INTO_CHOICE_ID_GENERATOR",table="JPA_ID_TABLE",pkColumnValue="FIT_INTO_CHOICE_ID",allocationSize=1)
@EntityListeners(AuditingEntityListener.class)
public class FitIntoChoice{

	private @Version Long version;
	private @JsonIgnore @LastModifiedDate LocalDateTime lastModifiedDate;
 
        @Id 
@GeneratedValue(strategy = GenerationType.TABLE,generator="FIT_INTO_CHOICE_ID_GENERATOR")
@Column(name="ID")
private Long id;

 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="FIT_INFO_ID")
private FitInfo fitInfo;
 
@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
@JoinColumn(name="FIT_INFO_OPTION_ID")
private FitInfoOption fitInfoOption;
    
}