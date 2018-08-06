import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { Warranty } from '../model/warranty';
import { WarrantyRest } from '../model/warranty.rest';

@Injectable()
export class WarrantyService extends CommonHttp {

	constructor( httpClient: HttpClient, warrantyRest: WarrantyRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/warrantys/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}