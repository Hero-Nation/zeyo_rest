import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { ItemDrycleaningMap } from './model/item_drycleaning_map'; 



describe( "ItemDrycleaningMap REST test", function() {

	let http: HttpClient;

	let get_test_item_drycleaning_map: ItemDrycleaningMap;
	let put_test_item_drycleaning_map: ItemDrycleaningMap;
	let patch_test_item_drycleaning_map: ItemDrycleaningMap;
	let delete_test_item_drycleaning_map: ItemDrycleaningMap;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/item_drycleaning_maps`,
			( done ) => {
				
				http.head( `/api/item_drycleaning_maps` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/item_drycleaning_maps`,
		( done ) => {
			
			http.options( `/api/item_drycleaning_maps` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/item_drycleaning_maps`,
		( done ) => {
			http.get<ItemDrycleaningMap>( `/api/item_drycleaning_maps` ).subscribe( R => {


				get_test_item_drycleaning_map = R._embedded.item_drycleaning_maps[0];
				put_test_item_drycleaning_map = R._embedded.item_drycleaning_maps[1];
				patch_test_item_drycleaning_map = R._embedded.item_drycleaning_maps[2];
				delete_test_item_drycleaning_map = R._embedded.item_drycleaning_maps[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/item_drycleaning_maps`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new ItemDrycleaningMap()
			
			
			params.drycan= "item_drycleaning_map POST drycan";
params.storecan= "item_drycleaning_map POST storecan";
params.detergent= "item_drycleaning_map POST detergent";
params.use_yn= "item_drycleaning_map POST use_yn";



			const req = http.post<ItemDrycleaningMap>( `/api/item_drycleaning_maps`, params, httpOptions )
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
	
	

	it( `HEAD /api/item_drycleaning_maps/item_drycleaning_map_id`,
			( done ) => {
				
				http.head( `/api/item_drycleaning_maps/${get_test_item_drycleaning_map.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/item_drycleaning_maps/item_drycleaning_map_id`,
		( done ) => {
			
			http.options( `/api/item_drycleaning_maps/${get_test_item_drycleaning_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/item_drycleaning_maps/item_drycleaning_map_id`,
		( done ) => {
			http.get<ItemDrycleaningMap>( `/api/item_drycleaning_maps/${get_test_item_drycleaning_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/item_drycleaning_maps/item_drycleaning_map_id`,
		( done ) => {
		
			put_test_item_drycleaning_map.drycan = "item_drycleaning_map PUT drycan";

put_test_item_drycleaning_map.storecan = "item_drycleaning_map PUT storecan";

put_test_item_drycleaning_map.detergent = "item_drycleaning_map PUT detergent";

put_test_item_drycleaning_map.use_yn = "item_drycleaning_map PUT use_yn";

			http.put<ItemDrycleaningMap>( `/api/item_drycleaning_maps/${put_test_item_drycleaning_map.id}`, put_test_item_drycleaning_map ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/item_drycleaning_maps/item_drycleaning_map_id`,
		( done ) => {


			patch_test_item_drycleaning_map.drycan = "item_drycleaning_map PATCH drycan";

patch_test_item_drycleaning_map.storecan = "item_drycleaning_map PATCH storecan";

patch_test_item_drycleaning_map.detergent = "item_drycleaning_map PATCH detergent";

patch_test_item_drycleaning_map.use_yn = "item_drycleaning_map PATCH use_yn";

			http.patch<ItemDrycleaningMap>( `/api/item_drycleaning_maps/${patch_test_item_drycleaning_map.id}`, patch_test_item_drycleaning_map ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/item_drycleaning_maps/item_drycleaning_map_id`,
		( done ) => {

			http.delete<ItemDrycleaningMap>( `/api/item_drycleaning_maps/${delete_test_item_drycleaning_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );