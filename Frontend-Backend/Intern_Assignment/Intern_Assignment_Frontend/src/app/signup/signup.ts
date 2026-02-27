import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { User } from '../service/user';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './signup.html',
  styleUrls: ['./signup.css']
})
export class SignupComponent {
  signupFields = [
    { type: 'text', name: 'username', placeholder: 'Username', value: '' },
    { type: 'email', name: 'email', placeholder: 'Email', value: '' },
    { type: 'password', name: 'password', placeholder: 'Password', value: '' }
  ];

  constructor(private userService: User) {}

  onSignup() {
    const username = this.signupFields.find(f => f.name === 'username')?.value || '';
    const email = this.signupFields.find(f => f.name === 'email')?.value || '';
    const password = this.signupFields.find(f => f.name === 'password')?.value || '';

    this.userService.signup({ name: username, email, password });
    alert('Signup successful!');
  }
}
