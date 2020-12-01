import {Teacher} from "./teacher.model";
import {Student} from "./student.model";
import {CheckingForm} from "./checkingForm.model";
import {Subject} from "./subject.model";

export class Course {
  public id: number;
  public name: string;
  public teacher: Teacher;
  public subject: Subject;
  public students: Student[];
  public checkingForms: CheckingForm[];
  public day: number;
  public time: number;

  //TODO add serializable class to models

  // deserialize(input: any): this {
  //   // Assign input to our object BEFORE deserialize our cars to prevent already deserialized cars from being overwritten.
  //   return Object.assign(this, input);
  //
  //   // Iterate over all cars for our user and map them to a proper `Car` model
  //   this.students = input.students.map( student => new Student().deserialize(student));
  //   return this;
  // }

}
