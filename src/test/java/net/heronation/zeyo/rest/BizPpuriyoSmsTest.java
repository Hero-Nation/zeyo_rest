package net.heronation.zeyo.rest;
 
import com.daou.BizRecv;
import com.daou.BizSend;
import com.daou.entity.ReportMsgEntity;
import com.daou.entity.SendMsgEntity;


public class BizPpuriyoSmsTest {

	/**
	 * 메시지 전송 테스트
	 */
	private void send() {

		BizSend bs        = new BizSend();    // 메시지 전송 클래스
		SendMsgEntity sme = null;             // 메시지 클래스

		/* Console 로그 설정 */
		// Console 에서 로그를 확인할 경우 설정
		bs.setLogEnabled(true);

		/* 서버 연결 시작 & 인증 */
		// ip   = biz.ppurio.com = 211.189.43.25
		// port = 5000 / 15001
		bs.doBegin("biz.ppurio.com", 5000, "user_id", "user_pw");

		/* 전송할 파일 경로 설정 */
		// 전송할 파일이 있을 경우 설정
		// ex. FAX, PHONE, MMS 등
//		bs.setFilePath("./spool");

		/* 블랙리스트 파일 경로 설정 */
		// 블랙리스트를 사용할 경우 설정
//		bs.setBlkPath("./blk");

		/* PING-PONG */
		// 장시간 연결하여 메시지를 전송할 경우 연결이 끊어지지 않기 위해 실행
		// 이전 호출 시점으로부터 30초 이상 지난 경우에만 PING 을 전송하도록 함수내  정의되어 있음
		// 실행하지 않아도 메시지 전송에는 영향을 미치지 않으나 안전한 연결을 위해 주기적으로 실행하길 권장
//		boolean bool = bs.sendPing();
//		System.out.println("Result of a Ping=" + bool);

		/* 리포트 재요청 */
		// msgid 에 해당하는 메시지의 리포트가 시간이 지나도 오지 않는 경우 실행
//		bool = bs.reconfirmReport("");    // @param msgid    다우기술의 서버에서 정의한 message id
//		System.out.println("Result of Reconfirming=" + bool);

		/* 메시지 정의 */
		// 다음의 setter 를 사용하여 필요한 정보를 정의
		// ex. SMS 의 경우, MSG_TYPE, DEST_PHONE, SEND_PHONE, MSG_BODY 정보를 정의
		sme = new SendMsgEntity();
		sme.setCMID      ("");    // 데이터 id
		sme.setMSG_TYPE  (0 );    // 데이터 타입 (SMS 0 / WAP 1 / FAX 2 / PHONE 3 / SMS_INBOUND 4 / MMS 5)
		sme.setSEND_TIME ("");    // 발송 (예약) 시간 (Unix Time, 정의하지 않을 경우 즉시 발송)
		sme.setDEST_PHONE("");    // 받는 사람 전화 번호
		sme.setDEST_NAME ("");    // 받는 사람 이름
		sme.setSEND_PHONE("");    // 보내는 사람 전화 번호
		sme.setSEND_NAME ("");    // 보내는 사람 이름
		sme.setSUBJECT   ("");    // (FAX/MMS) 제목, (SMS_INBOUND) 데이터 내용
		sme.setMSG_BODY  ("");    // 데이터 내용
		sme.setWAP_URL   ("");    // (WAP) URL 주소
		sme.setCOVER_FLAG(0 );    // (FAX) 표지 발송 옵션
		sme.setSMS_FLAG  (0 );    // (PHONE) PHONE 실패 시 문자 전송 옵션
		sme.setREPLY_FLAG(0 );    // (PHONE) 응답 받기 선택
		sme.setRETRY_CNT (0 );    // (FAX/PHONE) 재시도 회수 (5~10분 간격: 최대 3회)
		sme.setFAX_FILE  ("");    // (PHONE/FAX/MMS) 파일 전송시 파일 이름
		sme.setVXML_FILE ("");    // (PHONE) 음성 시나리오 파일 이름

		/* 메시지 전송 */
		try {
			String msgid = "";
			msgid = bs.sendMsg(sme);    // @param  sme       SendMsgEntity
			                            // @retrun String    다우기술의 서버에서 정의한 message id

			System.out.println("msgid=" + msgid);
		} catch (Exception ex) {
			System.out.println("Failed to Send a Msg" +
					", " + ex.getMessage());
		}

		/* 서버 연결 종료 */
		bs.doEnd();
	}


	/**
	 * 리포트 수신 테스트
	 */
	private void recv() {

		BizRecv br          = new BizRecv();    // 리포트 수신 클래스
		ReportMsgEntity rme = null;             // 리포트 클래스

		/* Console 로그 설정 */
		// Console 에서 로그를 확인할 경우 설정
		br.setLogEnabled(true);

		/* 서버 연결 시작 & 인증 */
		// ip   = biz.ppurio.com = 211.189.43.25
		// port = 5100 / 15100
//		br.doBegin(ip, port, user_id, user_pw);
		br.doBegin("biz.ppurio.com", 5100, "user_id", "user_pw");

		/* 리포트 수신 */
		// 최대 25시간 이내에 수신
		try {
			rme = br.recvReport();    // @return ReportMsgEntity
		} catch (Exception ex) {
			System.out.println("Failed to Receive a Report" +
					", " + ex.getMessage());
		}

		/* 리포트 확인 */
		if (rme != null) {
			// 다음의 getter 를 사용하여 필요한 정보를 확인
			rme.getCMID();           // 데이터 id
			rme.getUMID();           // 다우기술의 서버에서 정의한 message id
			rme.getMSG_TYPE();       // 데이터 타입 (SMS 0 / WAP 1 / FAX 2 / PHONE 3 / SMS_INBOUND 4 / MMS 5)
			rme.getDEST_PHONE();     // 받는 사람 전화 번호
			rme.getDEST_NAME();      // 받는 사람 이름
			rme.getREPORT_TIME();    // 발송 완료 시간 (Unix Time)
			rme.getUSE_PAGE();       // (FAX) 발송 페이지 수
			rme.getUSE_TIME();       // (PHONE) 발송 소요 시간 (단위 : 초)
			rme.getCALL_STATUS();    // 데이터의 결과 리포트
			rme.getSN_RESULT();      // (PHONE) 설문 조사 응답 값
			rme.getWAP_INFO();       // 통신사 정보 (SKT/KTF/LGT)
			rme.getCINFO();          // Client Indexed Info

			rme.toString();          // 리포트 수신 내용
		}

		/* 서버 연결 종료 */
		br.doEnd();
	}


	public static void main(String []args) {

		BizPpuriyoSmsTest bt = new BizPpuriyoSmsTest();

		/* S. 메시지 전송 */
		System.out.println("- 메시지 전송 테스트");
		bt.send();
		/* E. 메시지 전송 */

		System.out.println("\n");

		/* S. 리포트 수신 */
		System.out.println("- 리포트 수신 테스트");
		bt.recv();
		/* E. 리포트 수신 */
	}

}
