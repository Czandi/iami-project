import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";
import {CourseService} from "../../../../services/course.service";

@Component({
  selector: 'app-single-course',
  templateUrl: './single-course.component.html',
  styleUrls: ['./single-course.component.css']
})
export class SingleCourseComponent implements OnInit, OnDestroy {
  id_course: string;
  private courseSingleServiceSub: Subscription;
  public course: any;
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
        console.log(data);
      });
    this.id_course = this.route.snapshot.paramMap.get('id');
  }

  ngOnDestroy(): void {
    this.courseSingleServiceSub.unsubscribe();
  }

}
