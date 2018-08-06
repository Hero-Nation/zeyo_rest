import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { Material } from '../model/material';
import { MaterialRest } from '../model/material.rest';

@Injectable()
export class MaterialService extends CommonHttp {

	constructor( httpClient: HttpClient, materialRest: MaterialRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/materials/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}