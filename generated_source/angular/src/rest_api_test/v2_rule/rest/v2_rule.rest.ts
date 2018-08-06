import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { V2Rule } from '../model/v2_rule';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class V2RuleRest extends CommonHttp {

	private target_url = '/api/v2_rules';

	page_all_v2_rule() {
		return this.httpClient.get<V2Rule>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_v2_rule( v2_rule: V2Rule ): Observable<V2Rule> {

		return this.httpClient.post<V2Rule>( this.target_url, v2_rule,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_v2_rule_by_id(v2_rule: V2Rule) {
		return this.httpClient.get<V2Rule>( `${this.target_url}/${v2_rule.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_v2_rule(v2_rule:V2Rule){ 

		return this.httpClient.patch<V2Rule>( `${this.target_url}/${v2_rule.id}`, v2_rule ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_v2_rule(v2_rule:V2Rule){
    	return this.httpClient.put<V2Rule>( `${this.target_url}/${v2_rule.id}`, v2_rule,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_v2_rule(v2_rule:V2Rule){ 

		return this.httpClient.delete<V2Rule>( `${this.target_url}/${v2_rule.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}