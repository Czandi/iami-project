import { Deserializable } from './deserializable.model';

export class Student implements Deserializable{
  public id: number;
  public name: string;
  public surname: string;

  deserialize(input: any): this {
    return Object.assign(this, input);
    return this;
  }
}
