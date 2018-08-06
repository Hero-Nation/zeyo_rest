import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { Category } from '../model/category';
import { CategoryRest } from '../model/category.rest';

@Injectable()
export class CategoryService extends CommonHttp {

	constructor( httpClient: HttpClient, categoryRest: CategoryRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/categorys/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}