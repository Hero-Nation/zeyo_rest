import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { Kindof } from '../model/kindof';
import { KindofRest } from '../model/kindof.rest';

@Injectable()
export class KindofService extends CommonHttp {

	constructor( httpClient: HttpClient, kindofRest: KindofRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/kindofs/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}