import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { ItemLaundryMap } from './model/item_laundry_map'; 



describe( "ItemLaundryMap REST test", function() {

	let http: HttpClient;

	let get_test_item_laundry_map: ItemLaundryMap;
	let put_test_item_laundry_map: ItemLaundryMap;
	let patch_test_item_laundry_map: ItemLaundryMap;
	let delete_test_item_laundry_map: ItemLaundryMap;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/item_laundry_maps`,
			( done ) => {
				
				http.head( `/api/item_laundry_maps` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/item_laundry_maps`,
		( done ) => {
			
			http.options( `/api/item_laundry_maps` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/item_laundry_maps`,
		( done ) => {
			http.get<ItemLaundryMap>( `/api/item_laundry_maps` ).subscribe( R => {


				get_test_item_laundry_map = R._embedded.item_laundry_maps[0];
				put_test_item_laundry_map = R._embedded.item_laundry_maps[1];
				patch_test_item_laundry_map = R._embedded.item_laundry_maps[2];
				delete_test_item_laundry_map = R._embedded.item_laundry_maps[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/item_laundry_maps`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new ItemLaundryMap()
			
			
			params.water= "item_laundry_map POST water";
params.machine= "item_laundry_map POST machine";
params.hand= "item_laundry_map POST hand";
params.water_temp= "item_laundry_map POST water_temp";
params.intensity= "item_laundry_map POST intensity";
params.detergent= "item_laundry_map POST detergent";
params.use_yn= "item_laundry_map POST use_yn";



			const req = http.post<ItemLaundryMap>( `/api/item_laundry_maps`, params, httpOptions )
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
	
	

	it( `HEAD /api/item_laundry_maps/item_laundry_map_id`,
			( done ) => {
				
				http.head( `/api/item_laundry_maps/${get_test_item_laundry_map.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/item_laundry_maps/item_laundry_map_id`,
		( done ) => {
			
			http.options( `/api/item_laundry_maps/${get_test_item_laundry_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/item_laundry_maps/item_laundry_map_id`,
		( done ) => {
			http.get<ItemLaundryMap>( `/api/item_laundry_maps/${get_test_item_laundry_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/item_laundry_maps/item_laundry_map_id`,
		( done ) => {
		
			put_test_item_laundry_map.water = "item_laundry_map PUT water";

put_test_item_laundry_map.machine = "item_laundry_map PUT machine";

put_test_item_laundry_map.hand = "item_laundry_map PUT hand";

put_test_item_laundry_map.water_temp = "item_laundry_map PUT water_temp";

put_test_item_laundry_map.intensity = "item_laundry_map PUT intensity";

put_test_item_laundry_map.detergent = "item_laundry_map PUT detergent";

put_test_item_laundry_map.use_yn = "item_laundry_map PUT use_yn";

			http.put<ItemLaundryMap>( `/api/item_laundry_maps/${put_test_item_laundry_map.id}`, put_test_item_laundry_map ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/item_laundry_maps/item_laundry_map_id`,
		( done ) => {


			patch_test_item_laundry_map.water = "item_laundry_map PATCH water";

patch_test_item_laundry_map.machine = "item_laundry_map PATCH machine";

patch_test_item_laundry_map.hand = "item_laundry_map PATCH hand";

patch_test_item_laundry_map.water_temp = "item_laundry_map PATCH water_temp";

patch_test_item_laundry_map.intensity = "item_laundry_map PATCH intensity";

patch_test_item_laundry_map.detergent = "item_laundry_map PATCH detergent";

patch_test_item_laundry_map.use_yn = "item_laundry_map PATCH use_yn";

			http.patch<ItemLaundryMap>( `/api/item_laundry_maps/${patch_test_item_laundry_map.id}`, patch_test_item_laundry_map ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/item_laundry_maps/item_laundry_map_id`,
		( done ) => {

			http.delete<ItemLaundryMap>( `/api/item_laundry_maps/${delete_test_item_laundry_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );