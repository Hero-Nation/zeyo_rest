import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { ItemMaterialMap } from '../model/item_material_map';
import { ItemMaterialMapRest } from '../model/item_material_map.rest';

@Injectable()
export class ItemMaterialMapService extends CommonHttp {

	constructor( httpClient: HttpClient, item_material_mapRest: ItemMaterialMapRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/item_material_maps/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}