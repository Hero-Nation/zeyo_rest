import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { DmodelMeasureMap } from '../model/dmodel_measure_map';
import { DmodelMeasureMapRest } from '../model/dmodel_measure_map.rest';

@Injectable()
export class DmodelMeasureMapService extends CommonHttp {

	constructor( httpClient: HttpClient, dmodel_measure_mapRest: DmodelMeasureMapRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/dmodel_measure_maps/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}