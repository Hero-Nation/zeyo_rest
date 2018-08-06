import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { Item } from '../model/item';
import { ItemRest } from '../model/item.rest';

@Injectable()
export class ItemService extends CommonHttp {

	constructor( httpClient: HttpClient, itemRest: ItemRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/items/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}