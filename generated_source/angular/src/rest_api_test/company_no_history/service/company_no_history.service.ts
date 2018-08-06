import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { CompanyNoHistory } from '../model/company_no_history';
import { CompanyNoHistoryRest } from '../model/company_no_history.rest';

@Injectable()
export class CompanyNoHistoryService extends CommonHttp {

	constructor( httpClient: HttpClient, company_no_historyRest: CompanyNoHistoryRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/company_no_historys/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}