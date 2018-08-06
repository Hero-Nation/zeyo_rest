import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { ItemShopmallMap } from '../model/item_shopmall_map';
import { ItemShopmallMapRest } from '../model/item_shopmall_map.rest';

@Injectable()
export class ItemShopmallMapService extends CommonHttp {

	constructor( httpClient: HttpClient, item_shopmall_mapRest: ItemShopmallMapRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/item_shopmall_maps/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}