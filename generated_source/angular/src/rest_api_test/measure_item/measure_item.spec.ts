import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { MeasureItem } from './model/measure_item'; 



describe( "MeasureItem REST test", function() {

	let http: HttpClient;

	let get_test_measure_item: MeasureItem;
	let put_test_measure_item: MeasureItem;
	let patch_test_measure_item: MeasureItem;
	let delete_test_measure_item: MeasureItem;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/measure_items`,
			( done ) => {
				
				http.head( `/api/measure_items` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/measure_items`,
		( done ) => {
			
			http.options( `/api/measure_items` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/measure_items`,
		( done ) => {
			http.get<MeasureItem>( `/api/measure_items` ).subscribe( R => {


				get_test_measure_item = R._embedded.measure_items[0];
				put_test_measure_item = R._embedded.measure_items[1];
				patch_test_measure_item = R._embedded.measure_items[2];
				delete_test_measure_item = R._embedded.measure_items[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/measure_items`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new MeasureItem()
			
			
			params.name= "measure_item POST name";
params.meta_desc= "measure_item POST meta_desc";
params.create_dt= "measure_item POST create_dt";
params.use_yn= "measure_item POST use_yn";



			const req = http.post<MeasureItem>( `/api/measure_items`, params, httpOptions )
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
	
	

	it( `HEAD /api/measure_items/measure_item_id`,
			( done ) => {
				
				http.head( `/api/measure_items/${get_test_measure_item.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/measure_items/measure_item_id`,
		( done ) => {
			
			http.options( `/api/measure_items/${get_test_measure_item.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/measure_items/measure_item_id`,
		( done ) => {
			http.get<MeasureItem>( `/api/measure_items/${get_test_measure_item.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/measure_items/measure_item_id`,
		( done ) => {
		
			put_test_measure_item.name = "measure_item PUT name";

put_test_measure_item.meta_desc = "measure_item PUT meta_desc";

put_test_measure_item.create_dt = "measure_item PUT create_dt";

put_test_measure_item.use_yn = "measure_item PUT use_yn";

			http.put<MeasureItem>( `/api/measure_items/${put_test_measure_item.id}`, put_test_measure_item ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/measure_items/measure_item_id`,
		( done ) => {


			patch_test_measure_item.name = "measure_item PATCH name";

patch_test_measure_item.meta_desc = "measure_item PATCH meta_desc";

patch_test_measure_item.create_dt = "measure_item PATCH create_dt";

patch_test_measure_item.use_yn = "measure_item PATCH use_yn";

			http.patch<MeasureItem>( `/api/measure_items/${patch_test_measure_item.id}`, patch_test_measure_item ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/measure_items/measure_item_id`,
		( done ) => {

			http.delete<MeasureItem>( `/api/measure_items/${delete_test_measure_item.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );