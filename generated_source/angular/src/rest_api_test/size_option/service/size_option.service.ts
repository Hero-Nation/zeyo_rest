import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { SizeOption } from '../model/size_option';
import { SizeOptionRest } from '../model/size_option.rest';

@Injectable()
export class SizeOptionService extends CommonHttp {

	constructor( httpClient: HttpClient, size_optionRest: SizeOptionRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/size_options/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}