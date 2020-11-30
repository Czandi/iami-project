import {Routes, RouterModule, Router} from "@angular/router";

import { LoginComponent } from "./components/login/login.component";
import { TeacherHubComponent } from "./components/teacher-hub/teacher-hub.component";
import {ListCourseComponent} from "./components/teacher-hub/list-course/list-course.component";
import {AddCourseComponent} from "./components/teacher-hub/add-course/add-course.component";
import {AddSubjectComponent} from "./components/teacher-hub/add-subject/add-subject.component";
import {AuthGuardGondekGuard} from "./services/auth-guard-gondek.guard";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {
    path: 'centrum-nauczyciela',
    component:TeacherHubComponent,
    canActivate: [AuthGuardGondekGuard],
    children:[
      {path: 'twoje-kursy', component:ListCourseComponent},
      {path: 'dodaj-kurs', component:AddCourseComponent},
      {path: 'dodaj-przedmiot', component:AddSubjectComponent},
  ]
  },

  {path: '**', component: LoginComponent }
]

export const appRoutingModule = RouterModule.forRoot( routes );
