import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { SubCategoryMeasureMap } from './model/sub_category_measure_map'; 



describe( "SubCategoryMeasureMap REST test", function() {

	let http: HttpClient;

	let get_test_sub_category_measure_map: SubCategoryMeasureMap;
	let put_test_sub_category_measure_map: SubCategoryMeasureMap;
	let patch_test_sub_category_measure_map: SubCategoryMeasureMap;
	let delete_test_sub_category_measure_map: SubCategoryMeasureMap;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/sub_category_measure_maps`,
			( done ) => {
				
				http.head( `/api/sub_category_measure_maps` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/sub_category_measure_maps`,
		( done ) => {
			
			http.options( `/api/sub_category_measure_maps` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/sub_category_measure_maps`,
		( done ) => {
			http.get<SubCategoryMeasureMap>( `/api/sub_category_measure_maps` ).subscribe( R => {


				get_test_sub_category_measure_map = R._embedded.sub_category_measure_maps[0];
				put_test_sub_category_measure_map = R._embedded.sub_category_measure_maps[1];
				patch_test_sub_category_measure_map = R._embedded.sub_category_measure_maps[2];
				delete_test_sub_category_measure_map = R._embedded.sub_category_measure_maps[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/sub_category_measure_maps`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new SubCategoryMeasureMap()
			
			
			params.use_yn= "sub_category_measure_map POST use_yn";



			const req = http.post<SubCategoryMeasureMap>( `/api/sub_category_measure_maps`, params, httpOptions )
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
	
	

	it( `HEAD /api/sub_category_measure_maps/sub_category_measure_map_id`,
			( done ) => {
				
				http.head( `/api/sub_category_measure_maps/${get_test_sub_category_measure_map.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/sub_category_measure_maps/sub_category_measure_map_id`,
		( done ) => {
			
			http.options( `/api/sub_category_measure_maps/${get_test_sub_category_measure_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/sub_category_measure_maps/sub_category_measure_map_id`,
		( done ) => {
			http.get<SubCategoryMeasureMap>( `/api/sub_category_measure_maps/${get_test_sub_category_measure_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/sub_category_measure_maps/sub_category_measure_map_id`,
		( done ) => {
		
			put_test_sub_category_measure_map.use_yn = "sub_category_measure_map PUT use_yn";

			http.put<SubCategoryMeasureMap>( `/api/sub_category_measure_maps/${put_test_sub_category_measure_map.id}`, put_test_sub_category_measure_map ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/sub_category_measure_maps/sub_category_measure_map_id`,
		( done ) => {


			patch_test_sub_category_measure_map.use_yn = "sub_category_measure_map PATCH use_yn";

			http.patch<SubCategoryMeasureMap>( `/api/sub_category_measure_maps/${patch_test_sub_category_measure_map.id}`, patch_test_sub_category_measure_map ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/sub_category_measure_maps/sub_category_measure_map_id`,
		( done ) => {

			http.delete<SubCategoryMeasureMap>( `/api/sub_category_measure_maps/${delete_test_sub_category_measure_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );