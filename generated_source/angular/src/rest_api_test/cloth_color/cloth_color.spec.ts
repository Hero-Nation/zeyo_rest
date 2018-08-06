import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { ClothColor } from './model/cloth_color'; 



describe( "ClothColor REST test", function() {

	let http: HttpClient;

	let get_test_cloth_color: ClothColor;
	let put_test_cloth_color: ClothColor;
	let patch_test_cloth_color: ClothColor;
	let delete_test_cloth_color: ClothColor;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/cloth_colors`,
			( done ) => {
				
				http.head( `/api/cloth_colors` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/cloth_colors`,
		( done ) => {
			
			http.options( `/api/cloth_colors` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/cloth_colors`,
		( done ) => {
			http.get<ClothColor>( `/api/cloth_colors` ).subscribe( R => {


				get_test_cloth_color = R._embedded.cloth_colors[0];
				put_test_cloth_color = R._embedded.cloth_colors[1];
				patch_test_cloth_color = R._embedded.cloth_colors[2];
				delete_test_cloth_color = R._embedded.cloth_colors[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/cloth_colors`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new ClothColor()
			
			
			params.name= "cloth_color POST name";
params.use_yn= "cloth_color POST use_yn";



			const req = http.post<ClothColor>( `/api/cloth_colors`, params, httpOptions )
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
	
	

	it( `HEAD /api/cloth_colors/cloth_color_id`,
			( done ) => {
				
				http.head( `/api/cloth_colors/${get_test_cloth_color.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/cloth_colors/cloth_color_id`,
		( done ) => {
			
			http.options( `/api/cloth_colors/${get_test_cloth_color.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/cloth_colors/cloth_color_id`,
		( done ) => {
			http.get<ClothColor>( `/api/cloth_colors/${get_test_cloth_color.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/cloth_colors/cloth_color_id`,
		( done ) => {
		
			put_test_cloth_color.name = "cloth_color PUT name";

put_test_cloth_color.use_yn = "cloth_color PUT use_yn";

			http.put<ClothColor>( `/api/cloth_colors/${put_test_cloth_color.id}`, put_test_cloth_color ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/cloth_colors/cloth_color_id`,
		( done ) => {


			patch_test_cloth_color.name = "cloth_color PATCH name";

patch_test_cloth_color.use_yn = "cloth_color PATCH use_yn";

			http.patch<ClothColor>( `/api/cloth_colors/${patch_test_cloth_color.id}`, patch_test_cloth_color ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/cloth_colors/cloth_color_id`,
		( done ) => {

			http.delete<ClothColor>( `/api/cloth_colors/${delete_test_cloth_color.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );