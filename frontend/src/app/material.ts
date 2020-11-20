import { NgModule } from '@angular/core';
import {MatFormFieldModule} from "@angular/material/form-field";
import { MatSliderModule } from "@angular/material/slider";
import { MatInputModule} from "@angular/material/input";


@NgModule({
  declarations: [],
  imports: [
    MatFormFieldModule,
    MatSliderModule,
    MatInputModule
  ],
  exports: [
    MatFormFieldModule,
    MatSliderModule,
    MatInputModule
  ]
})
export class MaterialModule { }
