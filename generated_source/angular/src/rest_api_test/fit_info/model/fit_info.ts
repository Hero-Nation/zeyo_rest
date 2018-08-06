import { Rest } from '../../common/model/rest';
import { SubCategoryFitInfoMap } from '../../sub_category_fit_info_map/model/sub_category_fit_info_map';
 import { FitInfoOption } from '../../fit_info_option/model/fit_info_option';
 import { FitIntoChoice } from '../../fit_into_choice/model/fit_into_choice';

export class FitInfo extends Rest{
	subCategoryFitInfoMap : SubCategoryFitInfoMap[];
id: any;

name : any;

meta_desc : any;

create_dt : any;

use_yn : any;

 fitInfoOption : FitInfoOption[];
 fitIntoChoice : FitIntoChoice[];
}