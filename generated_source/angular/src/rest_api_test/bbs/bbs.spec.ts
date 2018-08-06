import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { Bbs } from './model/bbs'; 



describe( "Bbs REST test", function() {

	let http: HttpClient;

	let get_test_bbs: Bbs;
	let put_test_bbs: Bbs;
	let patch_test_bbs: Bbs;
	let delete_test_bbs: Bbs;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/bbss`,
			( done ) => {
				
				http.head( `/api/bbss` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/bbss`,
		( done ) => {
			
			http.options( `/api/bbss` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/bbss`,
		( done ) => {
			http.get<Bbs>( `/api/bbss` ).subscribe( R => {


				get_test_bbs = R._embedded.bbss[0];
				put_test_bbs = R._embedded.bbss[1];
				patch_test_bbs = R._embedded.bbss[2];
				delete_test_bbs = R._embedded.bbss[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/bbss`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new Bbs()
			
			
			params.title= "bbs POST title";
params.bbs_content= "bbs POST bbs_content";
params.reply_content= "bbs POST reply_content";
params.create_dt= "bbs POST create_dt";
params.reply_dt= "bbs POST reply_dt";
params.status= "bbs POST status";
params.use_yn= "bbs POST use_yn";



			const req = http.post<Bbs>( `/api/bbss`, params, httpOptions )
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
	
	

	it( `HEAD /api/bbss/bbs_id`,
			( done ) => {
				
				http.head( `/api/bbss/${get_test_bbs.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/bbss/bbs_id`,
		( done ) => {
			
			http.options( `/api/bbss/${get_test_bbs.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/bbss/bbs_id`,
		( done ) => {
			http.get<Bbs>( `/api/bbss/${get_test_bbs.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/bbss/bbs_id`,
		( done ) => {
		
			put_test_bbs.title = "bbs PUT title";

put_test_bbs.bbs_content = "bbs PUT bbs_content";

put_test_bbs.reply_content = "bbs PUT reply_content";

put_test_bbs.create_dt = "bbs PUT create_dt";

put_test_bbs.reply_dt = "bbs PUT reply_dt";

put_test_bbs.status = "bbs PUT status";

put_test_bbs.use_yn = "bbs PUT use_yn";

			http.put<Bbs>( `/api/bbss/${put_test_bbs.id}`, put_test_bbs ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/bbss/bbs_id`,
		( done ) => {


			patch_test_bbs.title = "bbs PATCH title";

patch_test_bbs.bbs_content = "bbs PATCH bbs_content";

patch_test_bbs.reply_content = "bbs PATCH reply_content";

patch_test_bbs.create_dt = "bbs PATCH create_dt";

patch_test_bbs.reply_dt = "bbs PATCH reply_dt";

patch_test_bbs.status = "bbs PATCH status";

patch_test_bbs.use_yn = "bbs PATCH use_yn";

			http.patch<Bbs>( `/api/bbss/${patch_test_bbs.id}`, patch_test_bbs ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/bbss/bbs_id`,
		( done ) => {

			http.delete<Bbs>( `/api/bbss/${delete_test_bbs.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );