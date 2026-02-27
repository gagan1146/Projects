import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

interface RegisterInterface {
  username: string;
  email: string;
  password: string;
}

@Component({
  selector: 'app-register',
  imports: [FormsModule, CommonModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {
  registerData: RegisterInterface = { username: '', email: '', password: '' };
  constructor(private httpClient: HttpClient) {}
  onRegister() {
    console.log('Register attempt:', this.registerData);
    this.httpClient
      .post('http://localhost:8080/register', this.registerData, { observe: 'response' })
      .subscribe({
        next: (response) => {
          if (response.status === 200) {
            alert(`Registered user: ${this.registerData.username}`);
            console.log('User data:', response.body);
          }
        },
        error: (err) => {
          if (err.status === 400) {
            alert('Invalid registration data.');
          } else if (err.status === 409) {
            alert('User already exists.');
          } else {
            alert('An unexpected error occurred.');
          }
        },
        complete: () => {
          console.log('Register API call completed.');
        },
      });
  }
}
