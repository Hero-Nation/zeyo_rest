import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { SubCategoryMeasureMap } from '../model/sub_category_measure_map';
import { SubCategoryMeasureMapRest } from '../model/sub_category_measure_map.rest';

@Injectable()
export class SubCategoryMeasureMapService extends CommonHttp {

	constructor( httpClient: HttpClient, sub_category_measure_mapRest: SubCategoryMeasureMapRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/sub_category_measure_maps/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}