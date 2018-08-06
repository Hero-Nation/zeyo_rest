import { Rest } from '../../common/model/rest';
import { Kindof } from '../../kindof/model/kindof';
import { Member } from '../../member/model/member';

export class Bbs extends Rest{
	id: any;

kindof: Kindof;member: Member;title : any;

bbs_content : any;

reply_content : any;

create_dt : any;

reply_dt : any;

status : any;

use_yn : any;
}