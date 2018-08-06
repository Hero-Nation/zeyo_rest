import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { Shopmall } from '../model/shopmall';
import { ShopmallRest } from '../model/shopmall.rest';

@Injectable()
export class ShopmallService extends CommonHttp {

	constructor( httpClient: HttpClient, shopmallRest: ShopmallRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/shopmalls/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}