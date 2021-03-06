import { StudentRequest } from './../models/studentRequest.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class StudentService {
  private API_URL: string = 'http://localhost:4201/api/students';

  constructor(private http: HttpClient) {}

  public addStudent(student: StudentRequest): Observable<any> {
    return this.http.post(this.API_URL, student);
  }

  public getStudents(): Observable<any> {
    return this.http.get(this.API_URL);
  }
}
