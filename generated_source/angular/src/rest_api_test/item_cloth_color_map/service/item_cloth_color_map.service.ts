import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { ItemClothColorMap } from '../model/item_cloth_color_map';
import { ItemClothColorMapRest } from '../model/item_cloth_color_map.rest';

@Injectable()
export class ItemClothColorMapService extends CommonHttp {

	constructor( httpClient: HttpClient, item_cloth_color_mapRest: ItemClothColorMapRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/item_cloth_color_maps/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}