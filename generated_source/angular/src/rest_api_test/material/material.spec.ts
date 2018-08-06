import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { Material } from './model/material'; 



describe( "Material REST test", function() {

	let http: HttpClient;

	let get_test_material: Material;
	let put_test_material: Material;
	let patch_test_material: Material;
	let delete_test_material: Material;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/materials`,
			( done ) => {
				
				http.head( `/api/materials` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/materials`,
		( done ) => {
			
			http.options( `/api/materials` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/materials`,
		( done ) => {
			http.get<Material>( `/api/materials` ).subscribe( R => {


				get_test_material = R._embedded.materials[0];
				put_test_material = R._embedded.materials[1];
				patch_test_material = R._embedded.materials[2];
				delete_test_material = R._embedded.materials[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/materials`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new Material()
			
			
			params.name= "material POST name";
params.image= "material POST image";
params.meta_desc= "material POST meta_desc";
params.create_dt= "material POST create_dt";
params.use_yn= "material POST use_yn";



			const req = http.post<Material>( `/api/materials`, params, httpOptions )
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
	
	

	it( `HEAD /api/materials/material_id`,
			( done ) => {
				
				http.head( `/api/materials/${get_test_material.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/materials/material_id`,
		( done ) => {
			
			http.options( `/api/materials/${get_test_material.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/materials/material_id`,
		( done ) => {
			http.get<Material>( `/api/materials/${get_test_material.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/materials/material_id`,
		( done ) => {
		
			put_test_material.name = "material PUT name";

put_test_material.image = "material PUT image";

put_test_material.meta_desc = "material PUT meta_desc";

put_test_material.create_dt = "material PUT create_dt";

put_test_material.use_yn = "material PUT use_yn";

			http.put<Material>( `/api/materials/${put_test_material.id}`, put_test_material ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/materials/material_id`,
		( done ) => {


			patch_test_material.name = "material PATCH name";

patch_test_material.image = "material PATCH image";

patch_test_material.meta_desc = "material PATCH meta_desc";

patch_test_material.create_dt = "material PATCH create_dt";

patch_test_material.use_yn = "material PATCH use_yn";

			http.patch<Material>( `/api/materials/${patch_test_material.id}`, patch_test_material ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/materials/material_id`,
		( done ) => {

			http.delete<Material>( `/api/materials/${delete_test_material.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );