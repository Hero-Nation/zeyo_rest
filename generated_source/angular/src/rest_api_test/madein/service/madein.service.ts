import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { Madein } from '../model/madein';
import { MadeinRest } from '../model/madein.rest';

@Injectable()
export class MadeinService extends CommonHttp {

	constructor( httpClient: HttpClient, madeinRest: MadeinRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/madeins/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}