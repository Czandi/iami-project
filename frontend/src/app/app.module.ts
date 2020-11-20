import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavComponent } from './nav/nav.component';
import { LoginComponent } from './login/login.component';
import { MaterialModule} from "./material";

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    LoginComponent
  ],
  exports: [
    MaterialModule
  ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        MaterialModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
