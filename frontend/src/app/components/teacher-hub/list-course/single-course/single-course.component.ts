import { Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";
import {CourseService} from "../../../../services/course.service";
import {FormControl} from "@angular/forms";
import {Presence} from "../../../../models/presence.model";



@Component({
  selector: 'app-single-course',
  templateUrl: './single-course.component.html',
  styleUrls: ['./single-course.component.css']
})
export class SingleCourseComponent implements OnInit, OnDestroy{
  id_course: string;
  private courseSingleServiceSub: Subscription;
  private courseStudentsServiceSub: Subscription;
  public course: any;
  public studentsData: any;
  public dateList: any = ['init'];
  public checkboxForms: any = [];
  public presence: Presence [] = [];
  public isPresence: boolean = false;
  private today: any;
  public isChecked: boolean = true;




  constructor(
    private route: ActivatedRoute,
    private courseService:CourseService
  ) { }


  ngOnInit(): void {
    let today = new Date();
    let dd = String(today.getDate()).padStart(2, '0');
    let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    let yyyy = today.getFullYear();
    this.today = yyyy + '-' + mm + '-' + dd;
    console.log(this.today);
    this.id_course = this.route.snapshot.paramMap.get('id');

    this.courseStudentsServiceSub = this.courseService
      .getStudentsData(this.id_course)
      .subscribe( (data) =>{
        this.studentsData = data;
        console.log(data);
        for (let i=0; i < this.studentsData.dates.length; i++){
            if (this.studentsData.dates[i].includes(this.today)){
              this.isChecked = false;
            }
        }
      });

    this.courseSingleServiceSub = this.courseService
      .getCoursesById(this.id_course)
      .subscribe((data) => {
        this.course = data;
        for (let i=0; i<this.course.students.length; i++){
          this.checkboxForms.push({
            bool: new FormControl(false)
          });
        }
      });



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
    for (let i=0; i<this.course.students.length; i++){
      let tempPresence = new Presence();
      tempPresence.idStudent = this.course.students[i].id;
      tempPresence.presence = this.checkboxForms[i].bool.value;
      this.presence.push(tempPresence);
    }
    this.courseService.addPresence(this.id_course, this.presence).subscribe();
    this.presence = [];
  }

  checkPresence() {
    this.isPresence = false;
  }

  displayCourse() {
    this.isPresence = true;
  }
}
