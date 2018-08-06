import { Rest } from '../../common/model/rest';
import { Item } from '../../item/model/item';
import { Kindof } from '../../kindof/model/kindof';

export class Warranty extends Rest{
	item : Item[];
id: any;

kindof: Kindof;scope : any;

 
create_dt : any;use_yn : any;
}