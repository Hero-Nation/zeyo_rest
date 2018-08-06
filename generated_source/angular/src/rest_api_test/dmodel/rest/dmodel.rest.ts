import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { Dmodel } from '../model/dmodel';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class DmodelRest extends CommonHttp {

	private target_url = '/api/dmodels';

	page_all_dmodel() {
		return this.httpClient.get<Dmodel>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_dmodel( dmodel: Dmodel ): Observable<Dmodel> {

		return this.httpClient.post<Dmodel>( this.target_url, dmodel,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_dmodel_by_id(dmodel: Dmodel) {
		return this.httpClient.get<Dmodel>( `${this.target_url}/${dmodel.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_dmodel(dmodel:Dmodel){ 

		return this.httpClient.patch<Dmodel>( `${this.target_url}/${dmodel.id}`, dmodel ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_dmodel(dmodel:Dmodel){
    	return this.httpClient.put<Dmodel>( `${this.target_url}/${dmodel.id}`, dmodel,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_dmodel(dmodel:Dmodel){ 

		return this.httpClient.delete<Dmodel>( `${this.target_url}/${dmodel.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}