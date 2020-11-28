import {Routes, RouterModule, Router} from "@angular/router";

import { LoginComponent } from "./components/login/login.component";
import { TeacherHubComponent } from "./components/teacher-hub/teacher-hub.component";
import {ListCourseComponent} from "./components/teacher-hub/list-course/list-course.component";
import {AddCourseComponent} from "./components/teacher-hub/add-course/add-course.component";
import {AddSubjectComponent} from "./components/teacher-hub/add-subject/add-subject.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  // {path: 'dziennik', component:TeacherHubComponent, canActivate:[AuthGuard]},
  {
    path: 'centrum-nauczyciela',
    component:TeacherHubComponent,
    children:[
      {path: 'twoje-kursy', component:ListCourseComponent},
      {path: 'dodaj-kurs', component:AddCourseComponent},
      {path: 'dodaj-przedmiot', component:AddSubjectComponent},

  ]},
  // {path: 'dziennik', component:TeacherHubComponent},

  {path: '**', component: LoginComponent }
]

export const appRoutingModule = RouterModule.forRoot( routes );
