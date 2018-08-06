import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { FitInfoOption } from '../model/fit_info_option';
import { FitInfoOptionRest } from '../model/fit_info_option.rest';

@Injectable()
export class FitInfoOptionService extends CommonHttp {

	constructor( httpClient: HttpClient, fit_info_optionRest: FitInfoOptionRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/fit_info_options/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}