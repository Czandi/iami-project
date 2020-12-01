import {Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {SubjectService} from "../../../services/subject.service";
import {SubjectDataSource} from "../../../data-sources/subjectDataSource";

@Component({
  selector: 'app-add-subject',
  templateUrl: './add-subject.component.html',
  styleUrls: ['./add-subject.component.css']
})
export class AddSubjectComponent implements OnInit {

  form: any = {};
  errorMessage: string;
  isSuccess: boolean = false;
  dataSource: SubjectDataSource;
  displayedColumns =["subjectNo"]


  constructor(
    private subjectService: SubjectService
  ) { }

  ngOnInit(): void {
    this.dataSource = new SubjectDataSource(this.subjectService);
    this.dataSource.loadSubjects();
  }

  onSubmit() {
    this.subjectService.addSubject(this.form.name).subscribe(  (data) => {
      if (data.name.length >= 4){
        this.isSuccess = true;
        this.dataSource.loadSubjects();

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
