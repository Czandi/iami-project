import { Subscription } from 'rxjs';
import {
  Component,
  Input,
  OnChanges,
  OnInit,
  SimpleChanges,
  ViewChild,
} from '@angular/core';
import { SubjectService } from '../../../services/subject.service';
import { SubjectDataSource } from '../../../data-sources/subjectDataSource';

@Component({
  selector: 'app-add-subject',
  templateUrl: './add-subject.component.html',
  styleUrls: ['./add-subject.component.css'],
})
export class AddSubjectComponent implements OnInit {
  form: any = {};
  errorMessage: string;
  isSuccess: boolean = false;
  dataSource: SubjectDataSource;
  displayedColumns = ['subjectNo'];
  subjectSub: Subscription;
  subjectsNamesList: any = [];
  nameExists: boolean = false;

  constructor(private subjectService: SubjectService) {}

  ngOnInit(): void {
    this.subjectSub = this.subjectService.getSubjects().subscribe((data) => {
      data.forEach((subject) => {
        this.subjectsNamesList.push(subject.name);
      });

      console.log(this.subjectsNamesList);
    });

    this.dataSource = new SubjectDataSource(this.subjectService);
    this.dataSource.loadSubjects();
  }

  onSubmit() {
    let name = this.form.name;

    this.subjectService.addSubject(name).subscribe(
      (data) => {
        if (data.name.length < 4) {
          this.isSuccess = false;
        } else if (this.subjectsNamesList.indexOf(name) > -1) {
          this.nameExists = true;
        } else {
          this.isSuccess = true;
          this.dataSource.loadSubjects();
        }
      },
      (err) => {
        this.errorMessage = err.message;
      }
    );
  }

  clearInfo() {
    this.isSuccess = false;
    this.nameExists = false;
  }
}
