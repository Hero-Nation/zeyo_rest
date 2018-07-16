package net.heronation.zeyo.rest.bbs.repository;

import org.joda.time.DateTime;

import lombok.Value;
import net.heronation.zeyo.rest.kindof.repository.Kindof;
import net.heronation.zeyo.rest.member.repository.Member;

@Value
public class BbsDto {

	private Long id;

	private Kindof kindof;

	private Member member;

	private String title;

	private String bbsContent;

	private String replyContent;

	private DateTime createDt;

	private DateTime replyDt;

	private String status;

	private String useYn;

}