import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { CourseService } from '../../../../services/course.service';
import { FormControl } from '@angular/forms';
import { Presence } from '../../../../models/presence.model';

@Component({
  selector: 'app-single-course',
  templateUrl: './single-course.component.html',
  styleUrls: ['./single-course.component.css'],
})
export class SingleCourseComponent implements OnInit, OnDestroy {
  id_course: string;
  private courseSingleServiceSub: Subscription;
  private courseStudentsServiceSub: Subscription;
  public course: any;
  public studentsData: any = [];
  public dateList: any = ['init'];
  public newPresencesForms: any = [];
  public studentDataForms: any = [];
  public presence: Presence[] = [];
  public isPresence: boolean = false;
  public isChecked: boolean = true;
  private isClicked: boolean = true;

  constructor(
    private route: ActivatedRoute,
    private courseService: CourseService
  ) {}

  ngOnInit(): void {
    this.id_course = this.route.snapshot.paramMap.get('id');

    this.getStudentsData();

    this.getStudentsDataForms();
  }

  getCheckingsForNewPresences() {
    this.courseSingleServiceSub = this.courseService
      .getCoursesById(this.id_course)
      .subscribe((data) => {
        this.course = data;
        for (let i = 0; i < this.course.students.length; i++) {
          this.newPresencesForms.push({
            bool: new FormControl(false),
          });
        }
      });
  }

  getStudentsDataForms() {
    this.courseStudentsServiceSub = this.courseService
      .getStudentsData(this.id_course)
      .subscribe((data) => {});
  }

  getStudentsData() {
    this.courseStudentsServiceSub = this.courseService
      .getStudentsData(this.id_course)
      .subscribe((data) => {
        this.studentsData = data;
        this.isChecked = true;
        for (let i = 0; i < this.studentsData.dates.length; i++) {
          if (this.studentsData.dates[i].includes(this.getCurrentDate())) {
            this.isChecked = false;
          }
        }
        this.createFormControlsForData(data);
      });
  }

  createFormControlsForData(data) {
    for (let i = 0; i < data.studentsData.length; i++) {
      let currentData = data.studentsData[i];
      let currentStudentId = currentData.student.id;

      this.studentDataForms[currentStudentId] = {
        presencesForms: [],
        gradesForms: [],
      };

      for (let j = 0; j < currentData.presences.length; j++) {
        this.studentDataForms[currentStudentId].presencesForms.push({
          form: new FormControl(currentData.presences[j].presence),
          idPresence: currentData.presences[j].id,
        });
      }

      for (let j = 0; j < data.checkingForms.length; j++) {
        this.studentDataForms[currentStudentId].gradesForms.push({
          form: new FormControl(''),
          idCheckingForm: data.checkingForms[j].id,
          idGrade: '',
        });
      }

      console.log(this.studentDataForms);

      for (let j = 0; j < currentData.grades.length; j++) {
        for (
          let k = 0;
          k < this.studentDataForms[currentStudentId].gradesForms.length;
          k++
        ) {
          if (
            this.studentDataForms[currentStudentId].gradesForms[k]
              .idCheckingForm === data.studentsData[i].grades[j].idCheckingForm
          ) {
            this.studentDataForms[currentStudentId].gradesForms[
              k
            ].form.setValue(currentData.grades[j].grade.name);
          }
        }
      }
    }
  }

  getCurrentDate() {
    let today = new Date();
    let dd = String(today.getDate()).padStart(2, '0');
    let mm = String(today.getMonth() + 1).padStart(2, '0');
    let yyyy = today.getFullYear();
    return yyyy + '-' + mm + '-' + dd;
  }

  ngOnDestroy(): void {
    this.courseSingleServiceSub.unsubscribe();
    this.courseStudentsServiceSub.unsubscribe();
  }

  removeAbsence() {
    this.dateList = [];
  }

  addAbsence() {
    this.dateList.push('object');
  }

  checkPost() {
    if (this.isClicked) {
      for (let i = 0; i < this.course.students.length; i++) {
        let tempPresence = new Presence();
        tempPresence.idStudent = this.course.students[i].id;
        tempPresence.presence = this.newPresencesForms[i].bool.value;
        this.presence.push(tempPresence);
      }
      this.courseService.addPresence(this.id_course, this.presence).subscribe();
      this.presence = [];
      this.isClicked = false;
      window.location.reload(true);
    }
  }

  checkPresence() {
    this.isPresence = false;
    this.getStudentsDataForms();
  }

  displayCourse() {
    this.isPresence = true;
    this.getCheckingsForNewPresences();
  }

  deleteCol(date: String) {
    this.courseService
      .deletePresences(this.id_course, date)
      .subscribe((date) => {
        this.getStudentsData();
      });
  }

  updateData() {
    let newPresences = [];
    let newGrades = [];

    for (let i = 0; i < this.studentsData.studentsData.length; i++) {
      let currentStudentId = this.studentsData.studentsData[i].student.id;
      let currentPresence = this.studentDataForms[currentStudentId]
        .presencesForms;
      let currentGrade = this.studentDataForms[currentStudentId].gradesForms;

      for (let j = 0; j < currentPresence.length; j++) {
        newPresences.push({
          id: currentPresence[j].idPresence,
          presence: currentPresence[j].form.value,
        });
      }

      for (let j = 0; j < currentGrade.length; j++) {
        newGrades.push({
          idStudent: currentStudentId,
          idCheckingForm: this.studentsData.checkingForms[j].id,
          grade: currentGrade[j].form.value,
        });
      }
    }

    console.log(newPresences);
    console.log(newGrades);

    this.courseService
      .updateGrades(this.id_course, newGrades)
      .subscribe((data) => {
        console.log(data);
      });

    this.courseService
      .updatePresences(this.id_course, newPresences)
      .subscribe((data) => {
        console.log(data);
      });
  }
}
