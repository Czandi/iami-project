import { StudentService } from './../services/student.service';
import { CollectionViewer, DataSource } from '@angular/cdk/collections';
import { Subject } from '../models/subject.model';
import { BehaviorSubject, Observable } from 'rxjs';

export class StudentDataSource implements DataSource<Subject> {
  private studentsSubject = new BehaviorSubject<Subject[]>([]);

  constructor(private studentService: StudentService) {}

  connect(
    collectionViewer: CollectionViewer
  ): Observable<Subject[] | ReadonlyArray<Subject>> {
    return this.studentsSubject.asObservable();
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this.studentsSubject.complete();
  }

  loadStudents() {
    this.studentService
      .getStudents()
      .subscribe((subjects) => this.studentsSubject.next(subjects));
  }
}
