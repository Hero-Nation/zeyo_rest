import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { V2Rule } from '../model/v2_rule';
import { V2RuleRest } from '../model/v2_rule.rest';

@Injectable()
export class V2RuleService extends CommonHttp {

	constructor( httpClient: HttpClient, v2_ruleRest: V2RuleRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/v2_rules/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}