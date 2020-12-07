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
      .subscribe((data) => {
        console.log(data.studentsData);
      });
  }

  getStudentsData() {
    this.courseStudentsServiceSub = this.courseService
      .getStudentsData(this.id_course)
      .subscribe((data) => {
        this.studentsData = data;
        console.log(data);
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
      this.studentDataForms[data.studentsData[i].student.id] = {
        presencesForms: [],
        gradesForms: [],
      };
      for (let j = 0; j < data.studentsData[i].presences.length; j++) {
        this.studentDataForms[
          data.studentsData[i].student.id
        ].presencesForms.push({
          form: new FormControl(data.studentsData[i].presences[j].presence),
          idPresence: data.studentsData[i].presences[j].id,
        });
      }
      if ( data.studentsData[i].grades.length == 0){
        for (let j = 0; j < data.checkingForms.length; j++){
        this.studentDataForms[data.studentsData[i].student.id].gradesForms.push(
          {
            form: new FormControl(0),
            idGrade: j,
          }
        )}
      }
      else {
        for (let j = 0; j < data.studentsData[i].grades.length; j++) {
          this.studentDataForms[data.studentsData[i].student.id].gradesForms.push(
            {
              idStudent: data.studentsData[i].student.id,
              idCheckingForm: this.studentsData.checkingForms[j].id,
              grade: data.studentsData[i].grades[j].grade.name,
            }
          );
          console.log(this.studentDataForms);
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
        console.log(date);
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
      for (let j = 0; j < currentPresence.length; j++) {
        newPresences.push({
          id: currentPresence[j].idPresence,
          presence: currentPresence[j].form.value,
        });
      }
    }


      for (let i = 0; i < this.studentsData.studentsData.length; i++) {
        let currentStudentId = this.studentsData.studentsData[i].student.id;
        let currentGrade = this.studentDataForms[currentStudentId]
          .gradesForms;
        for (let j = 0; j < currentGrade.length; j++) {
          newGrades.push({
            idStudent: currentStudentId,
            idCheckingForm: this.studentsData.checkingForms[j].id,
            grade: currentGrade[j].form.value,
          });
        }
      }
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
