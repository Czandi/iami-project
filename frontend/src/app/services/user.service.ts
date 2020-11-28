import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL: String = "http://localhost:4201/api/test/";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  getTeacherHub(): Observable<any> {
    return this.http.get(API_URL + 'teacher', { responseType: 'text' });
  }

}
