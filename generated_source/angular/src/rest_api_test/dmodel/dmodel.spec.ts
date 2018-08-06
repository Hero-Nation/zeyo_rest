import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { Dmodel } from './model/dmodel'; 



describe( "Dmodel REST test", function() {

	let http: HttpClient;

	let get_test_dmodel: Dmodel;
	let put_test_dmodel: Dmodel;
	let patch_test_dmodel: Dmodel;
	let delete_test_dmodel: Dmodel;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/dmodels`,
			( done ) => {
				
				http.head( `/api/dmodels` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/dmodels`,
		( done ) => {
			
			http.options( `/api/dmodels` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/dmodels`,
		( done ) => {
			http.get<Dmodel>( `/api/dmodels` ).subscribe( R => {


				get_test_dmodel = R._embedded.dmodels[0];
				put_test_dmodel = R._embedded.dmodels[1];
				patch_test_dmodel = R._embedded.dmodels[2];
				delete_test_dmodel = R._embedded.dmodels[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/dmodels`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new Dmodel()
			
			
			params.title= "dmodel POST title";
params.controller= "dmodel POST controller";
params.svgdata= "dmodel POST svgdata";
params.create_dt= "dmodel POST create_dt";
params.update_dt= "dmodel POST update_dt";
params.use_yn= "dmodel POST use_yn";



			const req = http.post<Dmodel>( `/api/dmodels`, params, httpOptions )
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
	
	

	it( `HEAD /api/dmodels/dmodel_id`,
			( done ) => {
				
				http.head( `/api/dmodels/${get_test_dmodel.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/dmodels/dmodel_id`,
		( done ) => {
			
			http.options( `/api/dmodels/${get_test_dmodel.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/dmodels/dmodel_id`,
		( done ) => {
			http.get<Dmodel>( `/api/dmodels/${get_test_dmodel.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/dmodels/dmodel_id`,
		( done ) => {
		
			put_test_dmodel.title = "dmodel PUT title";

put_test_dmodel.controller = "dmodel PUT controller";

put_test_dmodel.svgdata = "dmodel PUT svgdata";

put_test_dmodel.create_dt = "dmodel PUT create_dt";put_test_dmodel.update_dt = "dmodel PUT update_dt";put_test_dmodel.use_yn = "dmodel PUT use_yn";

			http.put<Dmodel>( `/api/dmodels/${put_test_dmodel.id}`, put_test_dmodel ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/dmodels/dmodel_id`,
		( done ) => {


			patch_test_dmodel.title = "dmodel PATCH title";

patch_test_dmodel.controller = "dmodel PATCH controller";

patch_test_dmodel.svgdata = "dmodel PATCH svgdata";

patch_test_dmodel.create_dt = "dmodel PATCH create_dt";
patch_test_dmodel.update_dt = "dmodel PATCH update_dt";
patch_test_dmodel.use_yn = "dmodel PATCH use_yn";

			http.patch<Dmodel>( `/api/dmodels/${patch_test_dmodel.id}`, patch_test_dmodel ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/dmodels/dmodel_id`,
		( done ) => {

			http.delete<Dmodel>( `/api/dmodels/${delete_test_dmodel.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );