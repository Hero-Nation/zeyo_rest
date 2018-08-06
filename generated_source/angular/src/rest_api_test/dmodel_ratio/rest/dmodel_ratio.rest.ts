import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { DmodelRatio } from '../model/dmodel_ratio';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class DmodelRatioRest extends CommonHttp {

	private target_url = '/api/dmodel_ratios';

	page_all_dmodel_ratio() {
		return this.httpClient.get<DmodelRatio>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_dmodel_ratio( dmodel_ratio: DmodelRatio ): Observable<DmodelRatio> {

		return this.httpClient.post<DmodelRatio>( this.target_url, dmodel_ratio,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_dmodel_ratio_by_id(dmodel_ratio: DmodelRatio) {
		return this.httpClient.get<DmodelRatio>( `${this.target_url}/${dmodel_ratio.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_dmodel_ratio(dmodel_ratio:DmodelRatio){ 

		return this.httpClient.patch<DmodelRatio>( `${this.target_url}/${dmodel_ratio.id}`, dmodel_ratio ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_dmodel_ratio(dmodel_ratio:DmodelRatio){
    	return this.httpClient.put<DmodelRatio>( `${this.target_url}/${dmodel_ratio.id}`, dmodel_ratio,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_dmodel_ratio(dmodel_ratio:DmodelRatio){ 

		return this.httpClient.delete<DmodelRatio>( `${this.target_url}/${dmodel_ratio.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}