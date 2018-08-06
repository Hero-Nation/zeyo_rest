import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { ItemSizeOptionMap } from './model/item_size_option_map'; 



describe( "ItemSizeOptionMap REST test", function() {

	let http: HttpClient;

	let get_test_item_size_option_map: ItemSizeOptionMap;
	let put_test_item_size_option_map: ItemSizeOptionMap;
	let patch_test_item_size_option_map: ItemSizeOptionMap;
	let delete_test_item_size_option_map: ItemSizeOptionMap;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/item_size_option_maps`,
			( done ) => {
				
				http.head( `/api/item_size_option_maps` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/item_size_option_maps`,
		( done ) => {
			
			http.options( `/api/item_size_option_maps` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/item_size_option_maps`,
		( done ) => {
			http.get<ItemSizeOptionMap>( `/api/item_size_option_maps` ).subscribe( R => {


				get_test_item_size_option_map = R._embedded.item_size_option_maps[0];
				put_test_item_size_option_map = R._embedded.item_size_option_maps[1];
				patch_test_item_size_option_map = R._embedded.item_size_option_maps[2];
				delete_test_item_size_option_map = R._embedded.item_size_option_maps[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/item_size_option_maps`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new ItemSizeOptionMap()
			
			
			params.option_value= "item_size_option_map POST option_value";
params.use_yn= "item_size_option_map POST use_yn";



			const req = http.post<ItemSizeOptionMap>( `/api/item_size_option_maps`, params, httpOptions )
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
	
	

	it( `HEAD /api/item_size_option_maps/item_size_option_map_id`,
			( done ) => {
				
				http.head( `/api/item_size_option_maps/${get_test_item_size_option_map.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/item_size_option_maps/item_size_option_map_id`,
		( done ) => {
			
			http.options( `/api/item_size_option_maps/${get_test_item_size_option_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/item_size_option_maps/item_size_option_map_id`,
		( done ) => {
			http.get<ItemSizeOptionMap>( `/api/item_size_option_maps/${get_test_item_size_option_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/item_size_option_maps/item_size_option_map_id`,
		( done ) => {
		
			put_test_item_size_option_map.option_value = "item_size_option_map PUT option_value";

put_test_item_size_option_map.use_yn = "item_size_option_map PUT use_yn";

			http.put<ItemSizeOptionMap>( `/api/item_size_option_maps/${put_test_item_size_option_map.id}`, put_test_item_size_option_map ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/item_size_option_maps/item_size_option_map_id`,
		( done ) => {


			patch_test_item_size_option_map.option_value = "item_size_option_map PATCH option_value";

patch_test_item_size_option_map.use_yn = "item_size_option_map PATCH use_yn";

			http.patch<ItemSizeOptionMap>( `/api/item_size_option_maps/${patch_test_item_size_option_map.id}`, patch_test_item_size_option_map ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/item_size_option_maps/item_size_option_map_id`,
		( done ) => {

			http.delete<ItemSizeOptionMap>( `/api/item_size_option_maps/${delete_test_item_size_option_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );