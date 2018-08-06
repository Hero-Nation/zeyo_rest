import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { MeasureItem } from '../model/measure_item';
import { MeasureItemRest } from '../model/measure_item.rest';

@Injectable()
export class MeasureItemService extends CommonHttp {

	constructor( httpClient: HttpClient, measure_itemRest: MeasureItemRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/measure_items/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}