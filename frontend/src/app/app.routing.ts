import { AddStudentComponent } from './components/teacher-hub/add-student/add-student.component';
import { Routes, RouterModule, Router } from '@angular/router';

import { LoginComponent } from './components/login/login.component';
import { TeacherHubComponent } from './components/teacher-hub/teacher-hub.component';
import { ListCourseComponent } from './components/teacher-hub/list-course/list-course.component';
import { AddCourseComponent } from './components/teacher-hub/add-course/add-course.component';
import { AddSubjectComponent } from './components/teacher-hub/add-subject/add-subject.component';
import { AuthGuardGuard } from './services/auth-guard.guard';
import {SingleCourseComponent} from "./components/teacher-hub/list-course/single-course/single-course.component";

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: 'centrum-nauczyciela',
    component: TeacherHubComponent,
    canActivate: [AuthGuardGuard],
    children: [
      { path: 'twoje-kursy', component: ListCourseComponent },
      { path: 'twoje-kursy/:id', component: SingleCourseComponent },
      { path: 'dodaj-kurs', component: AddCourseComponent },
      { path: 'dodaj-przedmiot', component: AddSubjectComponent },
      { path: 'dodaj-studenta', component: AddStudentComponent },
    ],
  },

  { path: '**', component: LoginComponent },
];

export const appRoutingModule = RouterModule.forRoot(routes);
