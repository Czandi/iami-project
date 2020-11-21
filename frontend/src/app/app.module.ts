import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavComponent } from './components/nav/nav.component';
import { LoginComponent } from './components/login/login.component';
import { MaterialModule} from "./material";
import { appRoutingModule } from "./app.routing";
import { TeacherHubComponent } from "./components/teacher-hub/teacher-hub.component";

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    LoginComponent,
    TeacherHubComponent
  ],
  exports: [
    MaterialModule
  ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        MaterialModule,
        appRoutingModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
