import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { SubCategoryFitInfoMap } from '../model/sub_category_fit_info_map';
import { SubCategoryFitInfoMapRest } from '../model/sub_category_fit_info_map.rest';

@Injectable()
export class SubCategoryFitInfoMapService extends CommonHttp {

	constructor( httpClient: HttpClient, sub_category_fit_info_mapRest: SubCategoryFitInfoMapRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/sub_category_fit_info_maps/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}