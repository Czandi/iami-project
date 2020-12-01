import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Course } from '../models/course.model';

@Injectable({
  providedIn: 'root',
})
export class CourseService {
  private API_URL: string = 'http://localhost:4201/api/courses';

  constructor(private http: HttpClient) {}

  //   public addCourse(): Observable<any>{
  //
  //       return this.http.post(this.API_URL){
  //         "name": name,
  //         "id_teacher":
  //     };
  // }

  public getCourses(): Observable<any> {
    return this.http.get(this.API_URL);
  }

  // public getCourse(): {} {
  //   return this.httpService.get<Course>(`${this.API_URL}/user/${id}`).pipe(
  //     map(data => new Course().deserialize(data)),
  //     catchError(() => throwError('User not found'))
  //   );
  // }
  //
  // public getAllCourses(): Observable<Course[]> {
  //   return this.httpService.get<Course[]>(`${this.API_URL}/users`).pipe(
  //     map(data => data.map(data => new Course().deserialize(data)))
  //   );
  // }
}
