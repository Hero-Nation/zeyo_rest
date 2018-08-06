import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { ClothColor } from '../model/cloth_color';
import { ClothColorRest } from '../model/cloth_color.rest';

@Injectable()
export class ClothColorService extends CommonHttp {

	constructor( httpClient: HttpClient, cloth_colorRest: ClothColorRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/cloth_colors/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}