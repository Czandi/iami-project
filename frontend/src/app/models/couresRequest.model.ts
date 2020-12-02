import { CheckingForm } from './checkingForm.model';
export class CourseRequest {
  name: string;
  idTeacher: number;
  idSubject: number;
  day: number;
  time: string;
  idStudents: number[];
  checkingForms: CheckingForm[];
}
