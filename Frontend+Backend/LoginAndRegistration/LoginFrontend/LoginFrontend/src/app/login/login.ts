import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone:true,
  imports: [FormsModule,CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  loginData = { email: '', password: '' };
  onLogin() {
    console.log('Login attempt:', this.loginData);
    alert(`Logged in with email: ${this.loginData.email}`);
  }
}
