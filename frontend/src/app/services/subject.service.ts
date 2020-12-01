import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";



@Injectable({
  providedIn: 'root'
})
export class SubjectService {
  private API_URL: string = "http://localhost:4201/api/subjects";

  constructor(
    private http: HttpClient
  ) { }

  public addSubject(name: string): Observable<any> {
    if (name.length >= 4) {
      return this.http.post(this.API_URL,
        name,
      );
    }
  }

}
