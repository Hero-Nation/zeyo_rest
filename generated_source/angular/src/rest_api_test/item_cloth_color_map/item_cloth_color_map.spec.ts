import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { ItemClothColorMap } from './model/item_cloth_color_map'; 



describe( "ItemClothColorMap REST test", function() {

	let http: HttpClient;

	let get_test_item_cloth_color_map: ItemClothColorMap;
	let put_test_item_cloth_color_map: ItemClothColorMap;
	let patch_test_item_cloth_color_map: ItemClothColorMap;
	let delete_test_item_cloth_color_map: ItemClothColorMap;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/item_cloth_color_maps`,
			( done ) => {
				
				http.head( `/api/item_cloth_color_maps` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/item_cloth_color_maps`,
		( done ) => {
			
			http.options( `/api/item_cloth_color_maps` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/item_cloth_color_maps`,
		( done ) => {
			http.get<ItemClothColorMap>( `/api/item_cloth_color_maps` ).subscribe( R => {


				get_test_item_cloth_color_map = R._embedded.item_cloth_color_maps[0];
				put_test_item_cloth_color_map = R._embedded.item_cloth_color_maps[1];
				patch_test_item_cloth_color_map = R._embedded.item_cloth_color_maps[2];
				delete_test_item_cloth_color_map = R._embedded.item_cloth_color_maps[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/item_cloth_color_maps`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new ItemClothColorMap()
			
			
			params.use_yn= "item_cloth_color_map POST use_yn";



			const req = http.post<ItemClothColorMap>( `/api/item_cloth_color_maps`, params, httpOptions )
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
	
	

	it( `HEAD /api/item_cloth_color_maps/item_cloth_color_map_id`,
			( done ) => {
				
				http.head( `/api/item_cloth_color_maps/${get_test_item_cloth_color_map.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/item_cloth_color_maps/item_cloth_color_map_id`,
		( done ) => {
			
			http.options( `/api/item_cloth_color_maps/${get_test_item_cloth_color_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/item_cloth_color_maps/item_cloth_color_map_id`,
		( done ) => {
			http.get<ItemClothColorMap>( `/api/item_cloth_color_maps/${get_test_item_cloth_color_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/item_cloth_color_maps/item_cloth_color_map_id`,
		( done ) => {
		
			put_test_item_cloth_color_map.use_yn = "item_cloth_color_map PUT use_yn";

			http.put<ItemClothColorMap>( `/api/item_cloth_color_maps/${put_test_item_cloth_color_map.id}`, put_test_item_cloth_color_map ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/item_cloth_color_maps/item_cloth_color_map_id`,
		( done ) => {


			patch_test_item_cloth_color_map.use_yn = "item_cloth_color_map PATCH use_yn";

			http.patch<ItemClothColorMap>( `/api/item_cloth_color_maps/${patch_test_item_cloth_color_map.id}`, patch_test_item_cloth_color_map ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/item_cloth_color_maps/item_cloth_color_map_id`,
		( done ) => {

			http.delete<ItemClothColorMap>( `/api/item_cloth_color_maps/${delete_test_item_cloth_color_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );