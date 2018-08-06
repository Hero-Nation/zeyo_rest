import { Rest } from '../../common/model/rest';
import { FitInfo } from '../../fit_info/model/fit_info';
 import { FitIntoChoice } from '../../fit_into_choice/model/fit_into_choice';

export class FitInfoOption extends Rest{
	id: any;

fitInfo: FitInfo;name : any;

use_yn : any;

 fitIntoChoice : FitIntoChoice[];
}