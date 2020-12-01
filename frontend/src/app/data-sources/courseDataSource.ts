import { CourseService } from './../services/course.service';
import { CollectionViewer, DataSource } from '@angular/cdk/collections';
import { Subject } from '../models/subject.model';
import { BehaviorSubject, Observable } from 'rxjs';

export class CourseDataSource implements DataSource<Subject> {
  private coursesSubject = new BehaviorSubject<Subject[]>([]);

  constructor(private couresService: CourseService) {}

  connect(
    collectionViewer: CollectionViewer
  ): Observable<Subject[] | ReadonlyArray<Subject>> {
    return this.coursesSubject.asObservable();
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this.coursesSubject.complete();
  }

  loadCourses() {
    this.couresService
      .getCourses()
      .subscribe((courses) => this.coursesSubject.next(courses));
  }
}
