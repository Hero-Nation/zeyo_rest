import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { Dmodel } from '../model/dmodel';
import { DmodelRest } from '../model/dmodel.rest';

@Injectable()
export class DmodelService extends CommonHttp {

	constructor( httpClient: HttpClient, dmodelRest: DmodelRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/dmodels/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}