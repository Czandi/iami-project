import { NgModule } from '@angular/core';
import {MatFormFieldModule} from "@angular/material/form-field";
import { MatSliderModule } from "@angular/material/slider";
import { MatInputModule} from "@angular/material/input";
import { MatButtonModule } from "@angular/material/button";
import { MatDividerModule } from "@angular/material/divider";

@NgModule({
  declarations: [],
  imports: [
    MatFormFieldModule,
    MatSliderModule,
    MatInputModule,
    MatButtonModule,
    MatDividerModule
  ],
  exports: [
    MatFormFieldModule,
    MatSliderModule,
    MatInputModule,
    MatButtonModule,
    MatDividerModule
  ]
})
export class MaterialModule { }
