import {AfterViewInit, Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";
import {CourseService} from "../../../../services/course.service";
import {FormControl} from "@angular/forms";



@Component({
  selector: 'app-single-course',
  templateUrl: './single-course.component.html',
  styleUrls: ['./single-course.component.css']
})
export class SingleCourseComponent implements OnInit, OnDestroy{
  id_course: string;
  private courseSingleServiceSub: Subscription;
  public course: any;
  public dateList: any = ['init'];
  public checkboxForms: any = [];


  constructor(
    private route: ActivatedRoute,
    private courseService:CourseService
  ) { }


  ngOnInit(): void {
    this.id_course = this.route.snapshot.paramMap.get('id');
    this.courseSingleServiceSub = this.courseService
      .getCoursesById(this.id_course)
      .subscribe((data) => {
        this.course = data;
        for (let i=0; i<this.course.students.length; i++){
          this.checkboxForms.push({
            bool: new FormControl()
          });
        }
      });

  }

  ngOnDestroy(): void {
    this.courseSingleServiceSub.unsubscribe();
  }

  removeAbsence() {
    this.dateList = [];
  }

  addAbsence() {
    this.dateList.push('object');
  }

  checkPost() {

    for (let i=0; i<this.course.students.length; i++){
      console.log(this.checkboxForms[i].bool.value);
    }

  }

}
