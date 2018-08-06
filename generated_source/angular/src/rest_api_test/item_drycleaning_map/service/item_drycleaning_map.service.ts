import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { ItemDrycleaningMap } from '../model/item_drycleaning_map';
import { ItemDrycleaningMapRest } from '../model/item_drycleaning_map.rest';

@Injectable()
export class ItemDrycleaningMapService extends CommonHttp {

	constructor( httpClient: HttpClient, item_drycleaning_mapRest: ItemDrycleaningMapRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/item_drycleaning_maps/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}