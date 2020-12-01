import { StudentRequest } from './../../../models/studentRequest.model';
import { StudentDataSource } from './../../../data-sources/studentDataSource';
import { StudentService } from './../../../services/student.service';
import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { SubjectDataSource } from 'src/app/data-sources/subjectDataSource';

@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrls: ['./add-student.component.css'],
})
export class AddStudentComponent implements OnInit {
  form: any = {};
  errorMessage: string;
  isSuccess: boolean = false;
  dataSource: StudentDataSource;
  displayedColumns = ['subjectNo'];
  subjectSub: Subscription;
  subjectsNamesList: any = [];
  nameExists: boolean = false;

  constructor(private studentService: StudentService) {}

  ngOnInit(): void {
    this.subjectSub = this.studentService.getStudents().subscribe((data) => {
      data.forEach((subject) => {
        this.subjectsNamesList.push(subject.name);
      });
    });

    this.dataSource = new StudentDataSource(this.studentService);
    this.dataSource.loadStudents();
  }

  onSubmit() {
    let name = this.form.name.toLowerCase();
    let surname = this.form.surname.toLowerCase();

    let newStudent = new StudentRequest();

    newStudent.name = name;
    newStudent.surname = surname;

    this.studentService.addStudent(newStudent).subscribe(
      (data) => {
        if (data.name.length < 4) {
          this.isSuccess = false;
        } else if (this.subjectsNamesList.indexOf(name) > -1) {
          this.nameExists = true;
        } else {
          this.isSuccess = true;
          this.dataSource.loadStudents();
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
