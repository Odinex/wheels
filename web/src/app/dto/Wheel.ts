import {User} from '../_models';

export class Wheel {
  id: number;
  make: string;
  model: string;
  name: string;
  variant: string;
  user: User;

  constructor(id: number, make: string, model: string, name: string, variant: string, user: User) {
    this.id = id;
    this.make = make;
    this.model = model;
    this.name = name;
    this.variant = variant;
    this.user = user;
  }

}
