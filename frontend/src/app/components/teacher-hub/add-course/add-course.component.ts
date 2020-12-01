import { takeUntil } from 'rxjs/operators';
import { ReplaySubject, Subject } from 'rxjs';
import { StudentService } from './../../../services/student.service';
import { SubjectService } from './../../../services/subject.service';
import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import { Subscription } from 'rxjs';
import { FormControl } from '@angular/forms';
import { Student } from 'src/app/models/student.model';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css'],
})
export class AddCourseComponent implements OnInit, OnDestroy {
  public form: any = {};
  public subjectList;
  public studentsList;


  public filteredStudents: ReplaySubject<Student[]> = new ReplaySubject<
    Student[]
  >(1);

  public studentsCtrl: FormControl = new FormControl();
  public studentsFilterCtrl: FormControl = new FormControl();

  private subjectSub: Subscription;
  private studentSub: Subscription;
  private _onDestroy = new Subject<void>();

  constructor(
    private subjectService: SubjectService,
    private studentService: StudentService
  ) {}

  ngOnInit(): void {
    this.subjectSub = this.subjectService.getSubjects().subscribe((data) => {
      this.subjectList = data;
    });

    this.studentSub = this.studentService.getStudents().subscribe((data) => {
      this.studentsList = data;

      this.filteredStudents.next(this.studentsList.slice());

      this.studentsFilterCtrl.valueChanges
        .pipe(takeUntil(this._onDestroy))
        .subscribe(() => {
          this.filterStudents();
        });
    });
  }

  filterStudents() {
    let search = this.studentsFilterCtrl.value;
    if (!search) {
      this.filteredStudents.next(this.studentsList.slice());
      return;
    } else {
      search = search.toLowerCase();
    }

    this.filteredStudents.next(
      this.studentsList.filter(
        (student) =>
          student.name.toLowerCase().indexOf(search) > -1 ||
          student.surname.toLowerCase().indexOf(search) > -1
      )
    );
  }

  ngOnDestroy() {
    this.subjectSub.unsubscribe();
    this.studentSub.unsubscribe();
  }

  clearInfo() {}

  onSubmit() {
    console.log(this.studentsFilterCtrl)
    console.log(this.form.name)
    console.log(this.studentsCtrl)
    // this.courseModel = {
    //   name: ,
    //   id_teacher: ""
    // }
  }
}
