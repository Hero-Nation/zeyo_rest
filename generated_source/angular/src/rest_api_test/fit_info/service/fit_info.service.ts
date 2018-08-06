import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { FitInfo } from '../model/fit_info';
import { FitInfoRest } from '../model/fit_info.rest';

@Injectable()
export class FitInfoService extends CommonHttp {

	constructor( httpClient: HttpClient, fit_infoRest: FitInfoRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/fit_infos/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}