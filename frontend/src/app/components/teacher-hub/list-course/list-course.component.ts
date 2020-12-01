import { DateMapper } from './../../../shared/dayMapper';
import { Subscription } from 'rxjs';
import { CourseService } from './../../../services/course.service';
import { Component, OnDestroy, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-course',
  templateUrl: './list-course.component.html',
  styleUrls: ['./list-course.component.css'],
})
export class ListCourseComponent implements OnInit, OnDestroy {
  public coursesList;
  private courseServiceSub: Subscription;

  constructor(private courseService: CourseService) {}

  ngOnInit(): void {
    this.courseServiceSub = this.courseService
      .getCourses()
      .subscribe((data) => {
        this.coursesList = data;
        console.log(data);
      });
  }

  ngOnDestroy(): void {
    this.courseServiceSub.unsubscribe();
  }

  mapDay(day: Number) {
    return DateMapper.mapDayNumberToText(day);
  }
}
