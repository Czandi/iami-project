import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({ providedIn: 'root' })
export class AuthenticationService {

  private API_URL: String = "http://localhost:4201/api/auth/";

  constructor(private http: HttpClient) {

  }

  login(credentials): Observable<any> {
    return this.http.post(this.API_URL + 'signin',{
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
  }

}
