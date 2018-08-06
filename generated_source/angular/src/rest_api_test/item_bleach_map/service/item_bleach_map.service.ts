import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { ItemBleachMap } from '../model/item_bleach_map';
import { ItemBleachMapRest } from '../model/item_bleach_map.rest';

@Injectable()
export class ItemBleachMapService extends CommonHttp {

	constructor( httpClient: HttpClient, item_bleach_mapRest: ItemBleachMapRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/item_bleach_maps/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}