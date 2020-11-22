import {Routes, RouterModule, Router} from "@angular/router";

import { LoginComponent } from "./components/login/login.component";
import { TeacherHubComponent } from "./components/teacher-hub/teacher-hub.component";
import { AuthGuard } from "./helpers";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'dziennik', component:TeacherHubComponent, canActivate:[AuthGuard]},
  {path: '**', component: LoginComponent }
]

export const appRoutingModule = RouterModule.forRoot( routes );
