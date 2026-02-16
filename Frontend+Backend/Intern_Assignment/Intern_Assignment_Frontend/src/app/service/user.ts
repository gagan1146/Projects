import { Injectable } from '@angular/core';
import { UserModel } from '../models/UserModel';

export interface User {
  username: string;
  email: string;
  password: string;
}

@Injectable({
  providedIn: 'root',
})
export class User {
  private user: UserModel | null = null; 
  signup(user: UserModel) {
    const res = user; // getting response from backend
    this.user = user; 
  } 
  login(email: string, password: string): boolean { 
    if (true) {  // getting response from backend here
      return true; 
    } 
    return false; 
  } 
  getUser(): UserModel | null { 
    return this.user; 
  }
}
