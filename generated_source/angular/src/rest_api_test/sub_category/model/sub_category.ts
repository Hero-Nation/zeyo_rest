import { Rest } from '../../common/model/rest';
import { Item } from '../../item/model/item';
import { Category } from '../../category/model/category';
import { Dmodel } from '../../dmodel/model/dmodel';
 import { SubCategoryMeasureMap } from '../../sub_category_measure_map/model/sub_category_measure_map';
 import { SubCategoryFitInfoMap } from '../../sub_category_fit_info_map/model/sub_category_fit_info_map';
 import { SizeOption } from '../../size_option/model/size_option';
 import { V2Rule } from '../../v2_rule/model/v2_rule';
 import { V2Rule } from '../../v2_rule/model/v2_rule';

export class SubCategory extends Rest{
	item : Item[];
id: any;

category: Category;dmodel: Dmodel;name : any;

item_image : any;

cloth_image : any;

laundry_yn : any;

drycleaning_yn : any;

ironing_yn : any;

drymethod_yn : any;

bleach_yn : any;

create_dt : any;

use_yn : any;

 subCategoryMeasureMap : SubCategoryMeasureMap[];
 subCategoryFitInfoMap : SubCategoryFitInfoMap[];
 sizeOption : SizeOption[];
 v2Rule : V2Rule[];
 v2Rule : V2Rule[];
}