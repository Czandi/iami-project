import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {JwtHelperService} from "@auth0/angular-jwt";
import {Observable} from "rxjs";
import {TokenStorageService} from "./token-storage.service";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({ providedIn: 'root' })
export class AuthenticationService {

  private API_URL: String = "http://localhost:4201/api/auth/";
  private helper;
  constructor(
    private http: HttpClient,
    public token: TokenStorageService,
  ) {
    this.helper = new JwtHelperService();
  }

  login(credentials): Observable<any> {
    return this.http.post(this.API_URL + 'signin',{
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
  }

  public isAuthenticated(): boolean{
    return !this.token.token;
  }

}
