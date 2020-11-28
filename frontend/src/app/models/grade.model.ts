import {Deserializable} from "./deserializable.model";

export class Grade {

  public id: number;
  public id_course: number;
  public id_course_student: number;
  public grade: string;
  public id_checking_form: number;

  // deserialize(input: any): this {
  //   return Object.assign(this, input);
  // }

}
