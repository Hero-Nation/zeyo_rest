import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { ItemIroningMap } from '../model/item_ironing_map';
import { ItemIroningMapRest } from '../model/item_ironing_map.rest';

@Injectable()
export class ItemIroningMapService extends CommonHttp {

	constructor( httpClient: HttpClient, item_ironing_mapRest: ItemIroningMapRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/item_ironing_maps/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}