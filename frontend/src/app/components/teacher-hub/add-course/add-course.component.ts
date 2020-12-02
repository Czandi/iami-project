import { CourseService } from './../../../services/course.service';
import { DateMapper } from '../../../shared/dateMapper';
import { CourseRequest } from './../../../models/couresRequest.model';
import { takeUntil } from 'rxjs/operators';
import { ReplaySubject, Subject } from 'rxjs';
import { StudentService } from './../../../services/student.service';
import { SubjectService } from './../../../services/subject.service';
import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import { Student } from 'src/app/models/student.model';
import { Course } from '../../../models/course.model';
import { TokenStorageService } from '../../../services/token-storage.service';
import { CheckingForm } from '../../../models/checkingForm.model';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css'],
})
export class AddCourseComponent implements OnInit, OnDestroy {
  public form: any = {};
  public subjectList;
  public studentsList;
  public courseModel: {} = new Course();
  public checkingFormsList: [];
  public daysList;
  public checkingForms: any = [];
  public isSuccess: boolean = false;

  public filteredStudents: ReplaySubject<Student[]> = new ReplaySubject<
    Student[]
  >(1);

  public studentsCtrl: FormControl = new FormControl();
  public studentsFilterCtrl: FormControl = new FormControl();
  public subjectCtrl: FormControl = new FormControl();
  public dayCtrl: FormControl = new FormControl('Dzień', Validators.required);
  public timeCtrl: FormControl = new FormControl();
  public nameCtrl: FormControl = new FormControl();
  public checkingFormGroup: FormGroup;

  private subjectSub: Subscription;
  private studentSub: Subscription;
  private _onDestroy = new Subject<void>();
  private checkingFormCounter = 0;

  constructor(
    private subjectService: SubjectService,
    private studentService: StudentService,
    private courseService: CourseService,
    private tokenStorageService: TokenStorageService
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

    this.daysList = [
      'poniedziałek',
      'wtorek',
      'środa',
      'czwartek',
      'piątek',
      'sobota',
      'niedziela',
    ];

    this.checkingFormGroup = new FormGroup({
      name: new FormControl(),
      weight: new FormControl(),
    });

    this.addCheckingForm();
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

  clearInfo() {
    this.isSuccess = false;
  }

  onSubmit() {
    if (this.checkInputs()) {
      let name = this.nameCtrl.value.toLowerCase();
      let subject = this.subjectCtrl.value;
      let day = this.dayCtrl.value;
      let time = this.timeCtrl.value;
      let students = this.studentsCtrl.value;
      let checkingForms = this.getCheckingFormsValues();
      let teacher = this.tokenStorageService.getUser().id;
      this.isSuccess = true;

      let course = new CourseRequest();
      course.day = DateMapper.mapDayToNumber(day);
      course.checkingForms = checkingForms;
      course.time = time;
      course.idTeacher = teacher;
      course.idStudents = students;
      course.name = name;
      course.idSubject = subject;

      this.courseService.addCourse(course).subscribe((data) => {
        console.log(data);
      });
    }
  }

  addCheckingForm() {
    this.checkingForms.push({
      id: this.checkingFormCounter,
      nameForm: new FormControl(),
      weightForm: new FormControl(),
      data: new CheckingForm(),
    });

    this.checkingFormCounter++;
  }

  getCheckingFormsValues() {
    let formsArray = [];

    for (let i = 0; i < this.checkingForms.length; i++) {
      let checkingForm = new CheckingForm();

      let name = this.checkingForms[i].nameForm.value.toLowerCase();
      let weight = this.checkingForms[i].weightForm.value;

      checkingForm.name = name;
      checkingForm.weight = weight;

      formsArray.push(checkingForm);
    }

    return formsArray;
  }

  getValue() {
    for (let i = 0; i < this.checkingForms.length; i++) {
      let name = this.checkingForms[i].nameForm.value;
      let weight = this.checkingForms[i].weightForm.value;
      console.log(name, weight);
    }
  }

  changeColor(element) {
    element.classList.add('active');
  }

  deleteForm(element) {
    this.checkingForms.splice(this.checkingForms.indexOf(element), 1);
  }

  checkInputs(): boolean {
    if (this.studentsCtrl.value === '' || this.studentsCtrl.value === null) {
      return false;
    }
    if (this.subjectCtrl.value === '' || this.subjectCtrl.value === null) {
      return false;
    }
    if (this.dayCtrl.value === '' || this.dayCtrl.value === null) {
      return false;
    }
    if (this.timeCtrl.value === '' || this.timeCtrl.value === null) {
      return false;
    }
    if (this.nameCtrl.value === '' || this.nameCtrl.value === null) {
      return false;
    }

    if (!this.checkFormsValues()) {
      return false;
    }

    return true;
  }

  checkFormsValues(): boolean {
    for (let i = 0; i < this.checkingForms.length; i++) {
      if (
        this.checkingForms[i].nameForm.value === '' ||
        this.checkingForms[i].nameForm.value === null ||
        this.checkingForms[i].weightForm.value === '' ||
        this.checkingForms[i].weightForm.value === null
      ) {
        return false;
      }
      return true;
    }
  }
}
