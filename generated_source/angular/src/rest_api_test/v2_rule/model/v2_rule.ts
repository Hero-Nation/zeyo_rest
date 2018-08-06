import { Rest } from '../../common/model/rest';
import { SubCategory } from '../../sub_category/model/sub_category';
import { SubCategory } from '../../sub_category/model/sub_category';

export class V2Rule extends Rest{
	id: any;

title : any;

rule_type : any;

rule_message : any;

subCategory: SubCategory;first_include_child : any;

subCategory: SubCategory;second_include_child : any;

 
create_dt : any;use_yn : any;
}