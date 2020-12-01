import { Component, OnInit, ViewChild } from '@angular/core';
import { Course } from '../../models/course.model';
import { CourseService } from '../../services/course.service';
import { CollectionViewer, DataSource } from '@angular/cdk/collections';
import { Observable } from 'rxjs';
import { UserService } from '../../services/user.service';
import { TokenStorageService } from '../../services/token-storage.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-teacher-hub',
  templateUrl: './teacher-hub.component.html',
  styleUrls: ['./teacher-hub.component.css'],
})
export class TeacherHubComponent implements OnInit, DataSource<Course> {
  @ViewChild('submenu') submenu;
  @ViewChild('teacherCourses') teacherCourses;
  @ViewChild('addSubject') addSubject;

  private activeElement = null;
  private activeSubmenuElement = null;

  currentUser: any;

  columnsToDisplay = ['name', 'surname', 'grade'];

  content: string;

  constructor(
    private courseService: CourseService,
    private userService: UserService,
    private tokenStorageService: TokenStorageService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.userService.getTeacherHub().subscribe(
      (data) => {
        this.content = data;
      },
      (err) => {
        this.content = JSON.parse(err.error).message;
      }
    );
  }

  ngAfterViewInit() {
    this.activeElement = this.teacherCourses.nativeElement;
    this.activeElement.classList.add('active');
  }

  connect(
    collectionViewer: CollectionViewer
  ): Observable<Course[] | ReadonlyArray<Course>> {
    return undefined;
  }

  disconnect(collectionViewer: CollectionViewer): void {}

  // public getCourse(){
  //   this.courses = this.courseService.getCourse();
  // }

  logout(): void {
    this.tokenStorageService.signOut();
  }

  onTeacherCourses(element) {
    this.router.navigate(['twoje-kursy'], { relativeTo: this.activatedRoute });
    this.activateNavElement(element);
  }

  onAddCourse(element) {
    this.router.navigate(['dodaj-kurs'], { relativeTo: this.activatedRoute });
    this.activateNavElement(element);
  }

  onAddSubject(element) {
    this.router.navigate(['dodaj-przedmiot'], {
      relativeTo: this.activatedRoute,
    });
    this.activateSubNavElement(element);
  }

  onAddStudent(element) {
    this.router.navigate(['dodaj-studenta'], {
      relativeTo: this.activatedRoute,
    });
    this.activateSubNavElement(element);
  }

  activateNavElement(element) {
    if (this.activeElement !== null) {
      this.activeElement.classList.remove('active');
    }
    this.activeElement = element;
    this.activeElement.classList.add('active');

    if (this.checkIfSubmenuIsOpen()) {
      this.deactivateSubmenu();
    }
  }

  activateSubNavElement(element) {
    if (this.activeSubmenuElement !== null) {
      this.activeSubmenuElement.classList.remove('active');
    }
    this.activeSubmenuElement = element;
    this.activeSubmenuElement.classList.add('active');
  }

  activeSubmenu(element) {
    this.activateNavElement(element);
    this.onAddSubject(this.addSubject.nativeElement);
    this.submenu.nativeElement.classList.add('active');
  }

  deactivateSubmenu() {
    this.submenu.nativeElement.classList.remove('active');
  }

  checkIfSubmenuIsOpen(): boolean {
    return this.submenu.nativeElement.classList.contains('active');
  }
}
