import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { Brand } from '../model/brand';
import { BrandRest } from '../model/brand.rest';

@Injectable()
export class BrandService extends CommonHttp {

	constructor( httpClient: HttpClient, brandRest: BrandRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/brands/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}