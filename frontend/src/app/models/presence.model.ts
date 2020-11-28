import {Deserializable} from "./deserializable.model";

export class Presence {
  public id: number;
  public date: string;
  public presence: string;
  public id_course: number;
  public id_course_student: number;

  // deserialize(input: any): this {
  //   return Object.assign(this, input);
  // }

}
