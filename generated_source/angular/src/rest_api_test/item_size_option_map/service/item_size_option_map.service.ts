import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { ItemSizeOptionMap } from '../model/item_size_option_map';
import { ItemSizeOptionMapRest } from '../model/item_size_option_map.rest';

@Injectable()
export class ItemSizeOptionMapService extends CommonHttp {

	constructor( httpClient: HttpClient, item_size_option_mapRest: ItemSizeOptionMapRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/item_size_option_maps/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}