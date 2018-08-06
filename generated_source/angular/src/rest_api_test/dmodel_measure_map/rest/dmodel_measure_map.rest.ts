import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { DmodelMeasureMap } from '../model/dmodel_measure_map';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class DmodelMeasureMapRest extends CommonHttp {

	private target_url = '/api/dmodel_measure_maps';

	page_all_dmodel_measure_map() {
		return this.httpClient.get<DmodelMeasureMap>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_dmodel_measure_map( dmodel_measure_map: DmodelMeasureMap ): Observable<DmodelMeasureMap> {

		return this.httpClient.post<DmodelMeasureMap>( this.target_url, dmodel_measure_map,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_dmodel_measure_map_by_id(dmodel_measure_map: DmodelMeasureMap) {
		return this.httpClient.get<DmodelMeasureMap>( `${this.target_url}/${dmodel_measure_map.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_dmodel_measure_map(dmodel_measure_map:DmodelMeasureMap){ 

		return this.httpClient.patch<DmodelMeasureMap>( `${this.target_url}/${dmodel_measure_map.id}`, dmodel_measure_map ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_dmodel_measure_map(dmodel_measure_map:DmodelMeasureMap){
    	return this.httpClient.put<DmodelMeasureMap>( `${this.target_url}/${dmodel_measure_map.id}`, dmodel_measure_map,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_dmodel_measure_map(dmodel_measure_map:DmodelMeasureMap){ 

		return this.httpClient.delete<DmodelMeasureMap>( `${this.target_url}/${dmodel_measure_map.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}