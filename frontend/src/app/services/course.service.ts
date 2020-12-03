import { CourseRequest } from './../models/couresRequest.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Course } from '../models/course.model';
import {Presence} from "../models/presence.model";

@Injectable({
  providedIn: 'root',
})
export class CourseService {
  private API_URL: string = 'http://localhost:4201/api/courses';

  constructor(private http: HttpClient) {}

  public getCourses(): Observable<any> {
    return this.http.get(this.API_URL);
  }

  public getCoursesById(id: string): Observable<any>{
    return this.http.get(this.API_URL + '/' + id);
  }

  public addCourse(course: CourseRequest): Observable<any> {
    return this.http.post(this.API_URL, course);
  }

  public addPresence(id: string, presence: Presence[]): Observable<any>{
    console.log(presence);
    return this.http.post(this.API_URL + '/' + id + '/presences', presence);
  }
}
