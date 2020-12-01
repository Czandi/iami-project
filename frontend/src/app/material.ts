import { NgModule } from '@angular/core';
import {MatFormFieldModule} from "@angular/material/form-field";
import { MatSliderModule } from "@angular/material/slider";
import { MatInputModule} from "@angular/material/input";
import { MatButtonModule } from "@angular/material/button";
import { MatDividerModule } from "@angular/material/divider";
import { MatTableModule} from "@angular/material/table";
import {MatSelectModule} from "@angular/material/select";



@NgModule({
  declarations: [],
  imports: [
    MatFormFieldModule,
    MatSliderModule,
    MatInputModule,
    MatButtonModule,
    MatDividerModule,
    MatTableModule,
    MatSelectModule,
    MatFormFieldModule,
    NgxMatSelectSearchModule,
  ],
  exports: [
    MatFormFieldModule,
    MatSliderModule,
    MatInputModule,
    MatButtonModule,
    MatDividerModule,
    MatTableModule,
    MatSelectModule,
    MatFormFieldModule,
    NgxMatSelectSearchModule,
  ]
})
export class MaterialModule { }
