import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { ItemLaundryMap } from '../model/item_laundry_map';
import { ItemLaundryMapRest } from '../model/item_laundry_map.rest';

@Injectable()
export class ItemLaundryMapService extends CommonHttp {

	constructor( httpClient: HttpClient, item_laundry_mapRest: ItemLaundryMapRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/item_laundry_maps/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}