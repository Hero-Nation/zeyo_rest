import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { ItemDrymethodMap } from './model/item_drymethod_map'; 



describe( "ItemDrymethodMap REST test", function() {

	let http: HttpClient;

	let get_test_item_drymethod_map: ItemDrymethodMap;
	let put_test_item_drymethod_map: ItemDrymethodMap;
	let patch_test_item_drymethod_map: ItemDrymethodMap;
	let delete_test_item_drymethod_map: ItemDrymethodMap;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/item_drymethod_maps`,
			( done ) => {
				
				http.head( `/api/item_drymethod_maps` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/item_drymethod_maps`,
		( done ) => {
			
			http.options( `/api/item_drymethod_maps` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/item_drymethod_maps`,
		( done ) => {
			http.get<ItemDrymethodMap>( `/api/item_drymethod_maps` ).subscribe( R => {


				get_test_item_drymethod_map = R._embedded.item_drymethod_maps[0];
				put_test_item_drymethod_map = R._embedded.item_drymethod_maps[1];
				patch_test_item_drymethod_map = R._embedded.item_drymethod_maps[2];
				delete_test_item_drymethod_map = R._embedded.item_drymethod_maps[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/item_drymethod_maps`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new ItemDrymethodMap()
			
			
			params.machine_dry= "item_drymethod_map POST machine_dry";
params.nature_dry= "item_drymethod_map POST nature_dry";
params.dry_mode= "item_drymethod_map POST dry_mode";
params.hand_dry= "item_drymethod_map POST hand_dry";
params.use_yn= "item_drymethod_map POST use_yn";



			const req = http.post<ItemDrymethodMap>( `/api/item_drymethod_maps`, params, httpOptions )
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
	
	

	it( `HEAD /api/item_drymethod_maps/item_drymethod_map_id`,
			( done ) => {
				
				http.head( `/api/item_drymethod_maps/${get_test_item_drymethod_map.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/item_drymethod_maps/item_drymethod_map_id`,
		( done ) => {
			
			http.options( `/api/item_drymethod_maps/${get_test_item_drymethod_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/item_drymethod_maps/item_drymethod_map_id`,
		( done ) => {
			http.get<ItemDrymethodMap>( `/api/item_drymethod_maps/${get_test_item_drymethod_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/item_drymethod_maps/item_drymethod_map_id`,
		( done ) => {
		
			put_test_item_drymethod_map.machine_dry = "item_drymethod_map PUT machine_dry";

put_test_item_drymethod_map.nature_dry = "item_drymethod_map PUT nature_dry";

put_test_item_drymethod_map.dry_mode = "item_drymethod_map PUT dry_mode";

put_test_item_drymethod_map.hand_dry = "item_drymethod_map PUT hand_dry";

put_test_item_drymethod_map.use_yn = "item_drymethod_map PUT use_yn";

			http.put<ItemDrymethodMap>( `/api/item_drymethod_maps/${put_test_item_drymethod_map.id}`, put_test_item_drymethod_map ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/item_drymethod_maps/item_drymethod_map_id`,
		( done ) => {


			patch_test_item_drymethod_map.machine_dry = "item_drymethod_map PATCH machine_dry";

patch_test_item_drymethod_map.nature_dry = "item_drymethod_map PATCH nature_dry";

patch_test_item_drymethod_map.dry_mode = "item_drymethod_map PATCH dry_mode";

patch_test_item_drymethod_map.hand_dry = "item_drymethod_map PATCH hand_dry";

patch_test_item_drymethod_map.use_yn = "item_drymethod_map PATCH use_yn";

			http.patch<ItemDrymethodMap>( `/api/item_drymethod_maps/${patch_test_item_drymethod_map.id}`, patch_test_item_drymethod_map ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/item_drymethod_maps/item_drymethod_map_id`,
		( done ) => {

			http.delete<ItemDrymethodMap>( `/api/item_drymethod_maps/${delete_test_item_drymethod_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );