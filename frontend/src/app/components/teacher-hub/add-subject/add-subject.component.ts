import {Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {SubjectService} from "../../../services/subject.service";

@Component({
  selector: 'app-add-subject',
  templateUrl: './add-subject.component.html',
  styleUrls: ['./add-subject.component.css']
})
export class AddSubjectComponent implements OnInit {

  form: any = {};
  errorMessage: string;
  isSuccess: boolean = false;


  constructor(
    private subjectService: SubjectService
  ) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.subjectService.addSubject(this.form.name).subscribe(  (data) => {
      if (data.name.length >= 4){
        this.isSuccess = true;
      }else{
        this.isSuccess = false;
      }

      },
      (err) => {
        this.errorMessage = err.message;
      }
    );
  }

  clearInfo() {
    this.isSuccess = false;
  }
}
