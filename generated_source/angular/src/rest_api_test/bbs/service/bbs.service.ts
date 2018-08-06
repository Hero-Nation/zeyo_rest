import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { Bbs } from '../model/bbs';
import { BbsRest } from '../model/bbs.rest';

@Injectable()
export class BbsService extends CommonHttp {

	constructor( httpClient: HttpClient, bbsRest: BbsRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/bbss/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}