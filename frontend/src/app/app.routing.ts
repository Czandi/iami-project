import {Routes, RouterModule, Router} from "@angular/router";

import { LoginComponent } from "./components/login/login.component";
import { TeacherHubComponent } from "./components/teacher-hub/teacher-hub.component";

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'dziennik', component:TeacherHubComponent},
  {path: '**', component: LoginComponent }
]

export const appRoutingModule = RouterModule.forRoot( routes );
