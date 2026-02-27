import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { User } from '../service/user';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class LoginComponent {
  loginFields = [
    { type: 'email', name: 'email', placeholder: 'Email', value: '' },
    { type: 'password', name: 'password', placeholder: 'Password', value: '' }
  ];

  constructor(private userService: User) {}

  onLogin() {
    const email = this.loginFields.find(f => f.name === 'email')?.value || '';
    const password = this.loginFields.find(f => f.name === 'password')?.value || '';
    const success = this.userService.login(email, password);
    alert(success ? 'Login successful!' : 'Invalid credentials');
  }
}