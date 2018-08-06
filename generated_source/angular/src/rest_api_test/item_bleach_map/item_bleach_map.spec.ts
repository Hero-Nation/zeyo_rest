import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { ItemBleachMap } from './model/item_bleach_map'; 



describe( "ItemBleachMap REST test", function() {

	let http: HttpClient;

	let get_test_item_bleach_map: ItemBleachMap;
	let put_test_item_bleach_map: ItemBleachMap;
	let patch_test_item_bleach_map: ItemBleachMap;
	let delete_test_item_bleach_map: ItemBleachMap;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/item_bleach_maps`,
			( done ) => {
				
				http.head( `/api/item_bleach_maps` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/item_bleach_maps`,
		( done ) => {
			
			http.options( `/api/item_bleach_maps` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/item_bleach_maps`,
		( done ) => {
			http.get<ItemBleachMap>( `/api/item_bleach_maps` ).subscribe( R => {


				get_test_item_bleach_map = R._embedded.item_bleach_maps[0];
				put_test_item_bleach_map = R._embedded.item_bleach_maps[1];
				patch_test_item_bleach_map = R._embedded.item_bleach_maps[2];
				delete_test_item_bleach_map = R._embedded.item_bleach_maps[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/item_bleach_maps`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new ItemBleachMap()
			
			
			params.chlorine= "item_bleach_map POST chlorine";
params.oxygen= "item_bleach_map POST oxygen";
params.use_yn= "item_bleach_map POST use_yn";



			const req = http.post<ItemBleachMap>( `/api/item_bleach_maps`, params, httpOptions )
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
	
	

	it( `HEAD /api/item_bleach_maps/item_bleach_map_id`,
			( done ) => {
				
				http.head( `/api/item_bleach_maps/${get_test_item_bleach_map.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/item_bleach_maps/item_bleach_map_id`,
		( done ) => {
			
			http.options( `/api/item_bleach_maps/${get_test_item_bleach_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/item_bleach_maps/item_bleach_map_id`,
		( done ) => {
			http.get<ItemBleachMap>( `/api/item_bleach_maps/${get_test_item_bleach_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/item_bleach_maps/item_bleach_map_id`,
		( done ) => {
		
			put_test_item_bleach_map.chlorine = "item_bleach_map PUT chlorine";

put_test_item_bleach_map.oxygen = "item_bleach_map PUT oxygen";

put_test_item_bleach_map.use_yn = "item_bleach_map PUT use_yn";

			http.put<ItemBleachMap>( `/api/item_bleach_maps/${put_test_item_bleach_map.id}`, put_test_item_bleach_map ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/item_bleach_maps/item_bleach_map_id`,
		( done ) => {


			patch_test_item_bleach_map.chlorine = "item_bleach_map PATCH chlorine";

patch_test_item_bleach_map.oxygen = "item_bleach_map PATCH oxygen";

patch_test_item_bleach_map.use_yn = "item_bleach_map PATCH use_yn";

			http.patch<ItemBleachMap>( `/api/item_bleach_maps/${patch_test_item_bleach_map.id}`, patch_test_item_bleach_map ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/item_bleach_maps/item_bleach_map_id`,
		( done ) => {

			http.delete<ItemBleachMap>( `/api/item_bleach_maps/${delete_test_item_bleach_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );