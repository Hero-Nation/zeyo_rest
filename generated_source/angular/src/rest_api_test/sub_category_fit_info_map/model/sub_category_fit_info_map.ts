import { Rest } from '../../common/model/rest';
import { SubCategory } from '../../sub_category/model/sub_category';
import { FitInfo } from '../../fit_info/model/fit_info';

export class SubCategoryFitInfoMap extends Rest{
	id: any;

subCategory: SubCategory;fitInfo: FitInfo;use_yn : any;
}