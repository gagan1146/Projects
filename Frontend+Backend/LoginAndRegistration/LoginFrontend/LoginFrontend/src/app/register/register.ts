import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

interface RegisterInterface {
  username: string;
  email: string;
  password: string;
}

@Component({
  selector: 'app-register',
  imports: [FormsModule,CommonModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {
  registerData: RegisterInterface = { username: '', email: '', password: '' };
  onRegister() {
    console.log('Register attempt:', this.registerData);
    alert(`Registered user: ${this.registerData.username}`);
  }
}
