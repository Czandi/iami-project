import { Component, OnInit } from '@angular/core';
import {Course} from "../../models/course.model";
import {CourseService} from "../../services/course.service";
import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import {Observable} from "rxjs";
import {UserService} from "../../services/user.service";
import {TokenStorageService} from "../../services/token-storage.service";
import {ActivatedRoute, Router} from "@angular/router";


@Component({
  selector: 'app-teacher-hub',
  templateUrl: './teacher-hub.component.html',
  styleUrls: ['./teacher-hub.component.css']
})
export class TeacherHubComponent implements OnInit, DataSource<Course> {

  currentUser: any;

  columnsToDisplay = ['name', 'surname', 'grade']
  public course: Course = {
    "id": 1,
    "name": "iami",
    "teacher":{
      "id": 1,
      "name": "jan",
      "surname": "Kowalski"
    },
    "subject":{
      "id": 1,
      "name": "iami",
    },
    "students": [
      {
        "id": 2,
        "name": "Karol",
        "surname": "purwin"
      },
      {
        "id": 3,
        "name": "Karol",
        "surname": "purwin"
      },
      {
        "id": 4,
        "name": "Karol",
        "surname": "purwin"
      },{
        "id": 5,
        "name": "Karol",
        "surname": "purwin"
      },{
        "id": 6,
        "name": "Karol",
        "surname": "purwin"
      }
    ],
    "checkingForms": [
      {
        "id": 1,
        "name": "kolos"
      },
      {
        "id": 2,
        "name": "kolos"
      },
      {
        "id": 3,
        "name": "kolos"
      }
    ],
    "day": 0
  }

  content: string;

  constructor(
    private courseService: CourseService,
    private userService: UserService,
    private tokenStorageService: TokenStorageService,
    private router: Router,
    private activatedRoute: ActivatedRoute
    ) { }

  ngOnInit(): void {
    this.userService.getTeacherHub().subscribe(
      data =>{
        this.content = data;
      },
      err =>{
        this.content = JSON.parse(err.error).message;
      }
    )
  }

  connect(collectionViewer: CollectionViewer): Observable<Course[] | ReadonlyArray<Course>> {
    return undefined;
  }

  disconnect(collectionViewer: CollectionViewer): void {
  }

  // public getCourse(){
  //   this.courses = this.courseService.getCourse();
  // }

  logout(): void {
    this.tokenStorageService.signOut();
  }

  onTeacherCourses() {
    this.router.navigate(['twoje-kursy'], {relativeTo: this.activatedRoute})
  }

  onAddCourse() {
    this.router.navigate(['dodaj-kurs'], {relativeTo: this.activatedRoute})
  }

  onAddSubject() {
    this.router.navigate(['dodaj-przedmiot'], {relativeTo: this.activatedRoute})
  }
}
