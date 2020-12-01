import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import {Subject} from "../models/subject.model";
import {BehaviorSubject, Observable} from "rxjs";
import {SubjectService} from "../services/subject.service";

export class SubjectDataSource implements DataSource<Subject> {


  private subjectsSubject = new BehaviorSubject<Subject[]>([]);

  constructor(
    private subjectService: SubjectService,
  ) {}



  connect(collectionViewer: CollectionViewer): Observable<Subject[] | ReadonlyArray<Subject>> {
    return this.subjectsSubject.asObservable();
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this.subjectsSubject.complete();
  }

  loadSubjects(){
    this.subjectService.getSubjects().subscribe(subjects => this.subjectsSubject.next(subjects));
  }

}
