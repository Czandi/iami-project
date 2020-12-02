import { Teacher } from './teacher.model';
import { Student } from './student.model';
import { CheckingForm } from './checkingForm.model';
import { Subject } from './subject.model';
import {Deserializable} from "./deserializable.model";

export class Course {
  public id: number;
  public name: string;
  public teacher: Teacher;
  public subject: string;
  public students: Student;
  public checkingForms: CheckingForm[];
  public day: number;
  public time: number;

  // deserialize(input: any): Course {
  //   Object.assign(this, input);
  //   this.students = new Student();
  //   return this;
  // }
}
