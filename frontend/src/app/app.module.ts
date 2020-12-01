import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavComponent } from './components/nav/nav.component';
import { LoginComponent } from './components/login/login.component';
import { MaterialModule } from './material';
import { appRoutingModule } from './app.routing';
import { TeacherHubComponent } from './components/teacher-hub/teacher-hub.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AddCourseComponent } from './components/teacher-hub/add-course/add-course.component';
import { ListCourseComponent } from './components/teacher-hub/list-course/list-course.component';
import { AddSubjectComponent } from './components/teacher-hub/add-subject/add-subject.component';
import { SingleCourseComponent } from './components/teacher-hub/list-course/single-course/single-course.component';
import { authInterceptorProviders } from './helpers/auth.guard';
import { MatSelectModule } from '@angular/material/select';
import { NgxMatSelectSearchModule } from 'ngx-mat-select-search';
import { MatFormFieldModule } from '@angular/material/form-field';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    LoginComponent,
    TeacherHubComponent,
    AddCourseComponent,
    ListCourseComponent,
    AddSubjectComponent,
    SingleCourseComponent,
  ],
  exports: [MaterialModule],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    appRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    MatSelectModule,
    MatFormFieldModule,
    NgxMatSelectSearchModule,
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent],
})
export class AppModule {}
