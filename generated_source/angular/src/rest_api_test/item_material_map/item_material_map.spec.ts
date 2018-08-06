import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { ItemMaterialMap } from './model/item_material_map'; 



describe( "ItemMaterialMap REST test", function() {

	let http: HttpClient;

	let get_test_item_material_map: ItemMaterialMap;
	let put_test_item_material_map: ItemMaterialMap;
	let patch_test_item_material_map: ItemMaterialMap;
	let delete_test_item_material_map: ItemMaterialMap;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/item_material_maps`,
			( done ) => {
				
				http.head( `/api/item_material_maps` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/item_material_maps`,
		( done ) => {
			
			http.options( `/api/item_material_maps` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/item_material_maps`,
		( done ) => {
			http.get<ItemMaterialMap>( `/api/item_material_maps` ).subscribe( R => {


				get_test_item_material_map = R._embedded.item_material_maps[0];
				put_test_item_material_map = R._embedded.item_material_maps[1];
				patch_test_item_material_map = R._embedded.item_material_maps[2];
				delete_test_item_material_map = R._embedded.item_material_maps[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/item_material_maps`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new ItemMaterialMap()
			
			
			params.contain= "item_material_map POST contain";
params.use_yn= "item_material_map POST use_yn";



			const req = http.post<ItemMaterialMap>( `/api/item_material_maps`, params, httpOptions )
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
	
	

	it( `HEAD /api/item_material_maps/item_material_map_id`,
			( done ) => {
				
				http.head( `/api/item_material_maps/${get_test_item_material_map.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/item_material_maps/item_material_map_id`,
		( done ) => {
			
			http.options( `/api/item_material_maps/${get_test_item_material_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/item_material_maps/item_material_map_id`,
		( done ) => {
			http.get<ItemMaterialMap>( `/api/item_material_maps/${get_test_item_material_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/item_material_maps/item_material_map_id`,
		( done ) => {
		
			put_test_item_material_map.contain = "item_material_map PUT contain";

put_test_item_material_map.use_yn = "item_material_map PUT use_yn";

			http.put<ItemMaterialMap>( `/api/item_material_maps/${put_test_item_material_map.id}`, put_test_item_material_map ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/item_material_maps/item_material_map_id`,
		( done ) => {


			patch_test_item_material_map.contain = "item_material_map PATCH contain";

patch_test_item_material_map.use_yn = "item_material_map PATCH use_yn";

			http.patch<ItemMaterialMap>( `/api/item_material_maps/${patch_test_item_material_map.id}`, patch_test_item_material_map ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/item_material_maps/item_material_map_id`,
		( done ) => {

			http.delete<ItemMaterialMap>( `/api/item_material_maps/${delete_test_item_material_map.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );