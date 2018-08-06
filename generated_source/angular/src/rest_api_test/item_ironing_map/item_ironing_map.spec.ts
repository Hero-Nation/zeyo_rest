import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { ItemIroningMap } from './model/item_ironing_map'; 



describe( "ItemIroningMap REST test", function() {

	let http: HttpClient;

	let get_test_item_ironing_map: ItemIroningMap;
	let put_test_item_ironing_map: ItemIroningMap;
	let patch_test_item_ironing_map: ItemIroningMap;
	let delete_test_item_ironing_map: ItemIroningMap;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/item_ironing_maps`,
			( done ) => {
				
				http.head( `/api/item_ironing_maps` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/item_ironing_maps`,
		( done ) => {
			
			http.options( `/api/item_ironing_maps` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/item_ironing_maps`,
		( done ) => {
			http.get<ItemIroningMap>( `/api/item_ironing_maps` ).subscribe( R => {


				get_test_item_ironing_map = R._embedded.item_ironing_maps[0];
				put_test_item_ironing_map = R._embedded.item_ironing_maps[1];
				patch_test_item_ironing_map = R._embedded.item_ironing_maps[2];
				delete_test_item_ironing_map = R._embedded.item_ironing_maps[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/item_ironing_maps`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new ItemIroningMap()
			
			
			params.ironcan= "item_ironing_map POST ironcan";
params.addprotection= "item_ironing_map POST addprotection";
params.start_temp= "item_ironing_map POST start_temp";
params.end_temp= "item_ironing_map POST end_temp";
params.use_yn= "item_ironing_map POST use_yn";



			const req = http.post<ItemIroningMap>( `/api/item_ironing_maps`, params, httpOptions )
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
	
	

	it( `HEAD /api/item_ironing_maps/item_ironing_map_id`,
			( done ) => {
				
				http.head( `/api/item_ironing_maps/${get_test_item_ironing_map.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/item_ironing_maps/item_ironing_map_id`,
		( done ) => {
			
			http.options( `/api/item_ironing_maps/${get_test_item_ironing_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/item_ironing_maps/item_ironing_map_id`,
		( done ) => {
			http.get<ItemIroningMap>( `/api/item_ironing_maps/${get_test_item_ironing_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/item_ironing_maps/item_ironing_map_id`,
		( done ) => {
		
			put_test_item_ironing_map.ironcan = "item_ironing_map PUT ironcan";

put_test_item_ironing_map.addprotection = "item_ironing_map PUT addprotection";

put_test_item_ironing_map.start_temp = "item_ironing_map PUT start_temp";

put_test_item_ironing_map.end_temp = "item_ironing_map PUT end_temp";

put_test_item_ironing_map.use_yn = "item_ironing_map PUT use_yn";

			http.put<ItemIroningMap>( `/api/item_ironing_maps/${put_test_item_ironing_map.id}`, put_test_item_ironing_map ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/item_ironing_maps/item_ironing_map_id`,
		( done ) => {


			patch_test_item_ironing_map.ironcan = "item_ironing_map PATCH ironcan";

patch_test_item_ironing_map.addprotection = "item_ironing_map PATCH addprotection";

patch_test_item_ironing_map.start_temp = "item_ironing_map PATCH start_temp";

patch_test_item_ironing_map.end_temp = "item_ironing_map PATCH end_temp";

patch_test_item_ironing_map.use_yn = "item_ironing_map PATCH use_yn";

			http.patch<ItemIroningMap>( `/api/item_ironing_maps/${patch_test_item_ironing_map.id}`, patch_test_item_ironing_map ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/item_ironing_maps/item_ironing_map_id`,
		( done ) => {

			http.delete<ItemIroningMap>( `/api/item_ironing_maps/${delete_test_item_ironing_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );