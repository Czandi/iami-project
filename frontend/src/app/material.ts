import { NgModule } from '@angular/core';
import {MatFormFieldModule} from "@angular/material/form-field";
import { MatSliderModule } from "@angular/material/slider";
import { MatInputModule} from "@angular/material/input";
import { MatButtonModule } from "@angular/material/button";
import { MatDividerModule } from "@angular/material/divider";
import { MatTableModule} from "@angular/material/table";

@NgModule({
  declarations: [],
  imports: [
    MatFormFieldModule,
    MatSliderModule,
    MatInputModule,
    MatButtonModule,
    MatDividerModule,
    MatTableModule
  ],
  exports: [
    MatFormFieldModule,
    MatSliderModule,
    MatInputModule,
    MatButtonModule,
    MatDividerModule,
    MatTableModule
  ]
})
export class MaterialModule { }
