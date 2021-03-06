import { CourseRequest } from './../models/couresRequest.model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Course } from '../models/course.model';
import { Presence } from '../models/presence.model';

@Injectable({
  providedIn: 'root',
})
export class CourseService {
  private API_URL: string = 'http://localhost:4201/api/courses';

  constructor(private http: HttpClient) {}

  public getCourses(): Observable<any> {
    return this.http.get(this.API_URL);
  }

  public getCoursesById(id: string): Observable<any> {
    return this.http.get(this.API_URL + '/' + id);
  }

  public addCourse(course: CourseRequest): Observable<any> {
    return this.http.post(this.API_URL, course);
  }

  public addPresence(id: string, presence: Presence[]): Observable<any> {
    console.log(presence);
    return this.http.post(this.API_URL + '/' + id + '/presences', presence);
  }

  public getStudentsData(id: string): Observable<any> {
    return this.http.get(this.API_URL + '/' + id + '/students-data');
  }

  public deletePresences(id: String, date: String): Observable<any> {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      body: date,
    };

    return this.http.delete(this.API_URL + '/' + id + '/presences', options);
  }

  public updatePresences(id: String, presences: Array<any>): Observable<any> {
    return this.http.put(this.API_URL + '/' + id + '/presences', presences);
  }

  public updateGrades(id: String, grades: Array<any>): Observable<any> {
    return this.http.put(this.API_URL + '/' + id + '/grades', grades);
  }
}
