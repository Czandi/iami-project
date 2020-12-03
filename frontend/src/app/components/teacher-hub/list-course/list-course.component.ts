import { DateMapper } from '../../../shared/dateMapper';
import { Subscription } from 'rxjs';
import { CourseService } from './../../../services/course.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-list-course',
  templateUrl: './list-course.component.html',
  styleUrls: ['./list-course.component.css'],
})
export class ListCourseComponent implements OnInit, OnDestroy {
  public coursesList;
  private courseServiceSub: Subscription;

  constructor(
    private courseService: CourseService,
    private router: Router,
    private activatedRoute: ActivatedRoute
    ) {}

  ngOnInit(): void {
    this.courseServiceSub = this.courseService
      .getCourses()
      .subscribe((data) => {
        this.coursesList = data;
      });
  }

  ngOnDestroy(): void {
    this.courseServiceSub.unsubscribe();
  }

  listDetail(id: number){
    this.router.navigate([id], { relativeTo: this.activatedRoute });
  }

  mapDay(day: Number) {
    return DateMapper.mapDayNumberToText(day);
  }
}
