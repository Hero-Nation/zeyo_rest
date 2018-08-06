import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { ItemDrymethodMap } from '../model/item_drymethod_map';
import { ItemDrymethodMapRest } from '../model/item_drymethod_map.rest';

@Injectable()
export class ItemDrymethodMapService extends CommonHttp {

	constructor( httpClient: HttpClient, item_drymethod_mapRest: ItemDrymethodMapRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/item_drymethod_maps/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}