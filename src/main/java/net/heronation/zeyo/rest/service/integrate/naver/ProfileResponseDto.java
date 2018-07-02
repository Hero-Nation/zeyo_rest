package net.heronation.zeyo.rest.service.integrate.naver;

import lombok.Data;

@Data
public class ProfileResponseDto {
//    "id": "51554177",
//    "nickname": "\ud558\uc580\ucf54\ubfd4\uc18c",
//    "profile_image": "https:\/\/phinf.pstatic.net\/contact\/39\/2016\/4\/19\/superpeace_1461027919942.jpg",
//    "age": "40-49",
//    "gender": "M",
//    "email": "superpeace@naver.com",
//    "name": "\uae40\uc218\uc5b8",
//    "birthday": "07-30"
	private String id;
	private String nickname;
	private String profile_image;
	private String age;
	private String gender;
	private String email;
	private String name;
	private String birthday; 
}
