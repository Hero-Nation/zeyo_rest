import { Rest } from '../../common/model/rest';
import { CompanyNoHistory } from '../../company_no_history/model/company_no_history';
 import { Item } from '../../item/model/item';
 import { Brand } from '../../brand/model/brand';
 import { Shopmall } from '../../shopmall/model/shopmall';
 import { CompanyNoHistory } from '../../company_no_history/model/company_no_history';
 import { Bbs } from '../../bbs/model/bbs';

export class Member extends Rest{
	id: any;

member_id : any;

name : any;

password : any;

phone : any;

email : any;

companyNoHistory: CompanyNoHistory;manager : any;

manager_email : any;

manager_phone : any;

create_dt : any;

delete_dt : any;

use_yn : any;

 item : Item[];
 brand : Brand[];
 shopmall : Shopmall[];
 companyNoHistory : CompanyNoHistory[];
 bbs : Bbs[];
}