package net.heronation.zeyo.rest.company_no_history.repository;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import lombok.Value;
import net.heronation.zeyo.rest.member.repository.Member;

@Value
public class CompanyNoHistoryDto {

	private List<Member> members = new ArrayList<Member>();
	private Long id;

	private Member member;

	private String name;

	private String companyNo;

	private String beforeNo;

	private DateTime changeDt;

}