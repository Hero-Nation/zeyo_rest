import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { DmodelRatio } from '../model/dmodel_ratio';
import { DmodelRatioRest } from '../model/dmodel_ratio.rest';

@Injectable()
export class DmodelRatioService extends CommonHttp {

	constructor( httpClient: HttpClient, dmodel_ratioRest: DmodelRatioRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/dmodel_ratios/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}