import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { SubCategory } from '../model/sub_category';
import { SubCategoryRest } from '../model/sub_category.rest';

@Injectable()
export class SubCategoryService extends CommonHttp {

	constructor( httpClient: HttpClient, sub_categoryRest: SubCategoryRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/sub_categorys/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}