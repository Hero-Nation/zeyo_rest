import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { DmodelMeasureMap } from './model/dmodel_measure_map'; 



describe( "DmodelMeasureMap REST test", function() {

	let http: HttpClient;

	let get_test_dmodel_measure_map: DmodelMeasureMap;
	let put_test_dmodel_measure_map: DmodelMeasureMap;
	let patch_test_dmodel_measure_map: DmodelMeasureMap;
	let delete_test_dmodel_measure_map: DmodelMeasureMap;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/dmodel_measure_maps`,
			( done ) => {
				
				http.head( `/api/dmodel_measure_maps` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/dmodel_measure_maps`,
		( done ) => {
			
			http.options( `/api/dmodel_measure_maps` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/dmodel_measure_maps`,
		( done ) => {
			http.get<DmodelMeasureMap>( `/api/dmodel_measure_maps` ).subscribe( R => {


				get_test_dmodel_measure_map = R._embedded.dmodel_measure_maps[0];
				put_test_dmodel_measure_map = R._embedded.dmodel_measure_maps[1];
				patch_test_dmodel_measure_map = R._embedded.dmodel_measure_maps[2];
				delete_test_dmodel_measure_map = R._embedded.dmodel_measure_maps[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/dmodel_measure_maps`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new DmodelMeasureMap()
			
			
			params.min_value= "dmodel_measure_map POST min_value";
params.max_value= "dmodel_measure_map POST max_value";
params.use_yn= "dmodel_measure_map POST use_yn";



			const req = http.post<DmodelMeasureMap>( `/api/dmodel_measure_maps`, params, httpOptions )
				.subscribe(
				R => {
					console.log( R );
					done();
				},
				err => {
					console.log( "Error occured" );
					done();
				}
				);
		}
	);
	
	

	it( `HEAD /api/dmodel_measure_maps/dmodel_measure_map_id`,
			( done ) => {
				
				http.head( `/api/dmodel_measure_maps/${get_test_dmodel_measure_map.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/dmodel_measure_maps/dmodel_measure_map_id`,
		( done ) => {
			
			http.options( `/api/dmodel_measure_maps/${get_test_dmodel_measure_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/dmodel_measure_maps/dmodel_measure_map_id`,
		( done ) => {
			http.get<DmodelMeasureMap>( `/api/dmodel_measure_maps/${get_test_dmodel_measure_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/dmodel_measure_maps/dmodel_measure_map_id`,
		( done ) => {
		
			put_test_dmodel_measure_map.min_value = "dmodel_measure_map PUT min_value";

put_test_dmodel_measure_map.max_value = "dmodel_measure_map PUT max_value";

put_test_dmodel_measure_map.use_yn = "dmodel_measure_map PUT use_yn";

			http.put<DmodelMeasureMap>( `/api/dmodel_measure_maps/${put_test_dmodel_measure_map.id}`, put_test_dmodel_measure_map ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/dmodel_measure_maps/dmodel_measure_map_id`,
		( done ) => {


			patch_test_dmodel_measure_map.min_value = "dmodel_measure_map PATCH min_value";

patch_test_dmodel_measure_map.max_value = "dmodel_measure_map PATCH max_value";

patch_test_dmodel_measure_map.use_yn = "dmodel_measure_map PATCH use_yn";

			http.patch<DmodelMeasureMap>( `/api/dmodel_measure_maps/${patch_test_dmodel_measure_map.id}`, patch_test_dmodel_measure_map ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/dmodel_measure_maps/dmodel_measure_map_id`,
		( done ) => {

			http.delete<DmodelMeasureMap>( `/api/dmodel_measure_maps/${delete_test_dmodel_measure_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );