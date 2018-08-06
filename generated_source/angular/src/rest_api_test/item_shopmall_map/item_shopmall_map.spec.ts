import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { ItemShopmallMap } from './model/item_shopmall_map'; 



describe( "ItemShopmallMap REST test", function() {

	let http: HttpClient;

	let get_test_item_shopmall_map: ItemShopmallMap;
	let put_test_item_shopmall_map: ItemShopmallMap;
	let patch_test_item_shopmall_map: ItemShopmallMap;
	let delete_test_item_shopmall_map: ItemShopmallMap;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/item_shopmall_maps`,
			( done ) => {
				
				http.head( `/api/item_shopmall_maps` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/item_shopmall_maps`,
		( done ) => {
			
			http.options( `/api/item_shopmall_maps` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/item_shopmall_maps`,
		( done ) => {
			http.get<ItemShopmallMap>( `/api/item_shopmall_maps` ).subscribe( R => {


				get_test_item_shopmall_map = R._embedded.item_shopmall_maps[0];
				put_test_item_shopmall_map = R._embedded.item_shopmall_maps[1];
				patch_test_item_shopmall_map = R._embedded.item_shopmall_maps[2];
				delete_test_item_shopmall_map = R._embedded.item_shopmall_maps[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/item_shopmall_maps`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new ItemShopmallMap()
			
			
			params.use_yn= "item_shopmall_map POST use_yn";



			const req = http.post<ItemShopmallMap>( `/api/item_shopmall_maps`, params, httpOptions )
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
	
	

	it( `HEAD /api/item_shopmall_maps/item_shopmall_map_id`,
			( done ) => {
				
				http.head( `/api/item_shopmall_maps/${get_test_item_shopmall_map.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/item_shopmall_maps/item_shopmall_map_id`,
		( done ) => {
			
			http.options( `/api/item_shopmall_maps/${get_test_item_shopmall_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/item_shopmall_maps/item_shopmall_map_id`,
		( done ) => {
			http.get<ItemShopmallMap>( `/api/item_shopmall_maps/${get_test_item_shopmall_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/item_shopmall_maps/item_shopmall_map_id`,
		( done ) => {
		
			put_test_item_shopmall_map.use_yn = "item_shopmall_map PUT use_yn";

			http.put<ItemShopmallMap>( `/api/item_shopmall_maps/${put_test_item_shopmall_map.id}`, put_test_item_shopmall_map ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/item_shopmall_maps/item_shopmall_map_id`,
		( done ) => {


			patch_test_item_shopmall_map.use_yn = "item_shopmall_map PATCH use_yn";

			http.patch<ItemShopmallMap>( `/api/item_shopmall_maps/${patch_test_item_shopmall_map.id}`, patch_test_item_shopmall_map ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/item_shopmall_maps/item_shopmall_map_id`,
		( done ) => {

			http.delete<ItemShopmallMap>( `/api/item_shopmall_maps/${delete_test_item_shopmall_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );