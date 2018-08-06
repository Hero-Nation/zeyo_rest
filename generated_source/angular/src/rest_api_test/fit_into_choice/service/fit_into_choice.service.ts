import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { FitIntoChoice } from '../model/fit_into_choice';
import { FitIntoChoiceRest } from '../model/fit_into_choice.rest';

@Injectable()
export class FitIntoChoiceService extends CommonHttp {

	constructor( httpClient: HttpClient, fit_into_choiceRest: FitIntoChoiceRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/fit_into_choices/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}