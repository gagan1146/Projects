import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

interface LoginInterface {
  userId: string;
  password: string;
}

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  loginData: LoginInterface = { userId: '', password: '' };
  constructor(private httpClient: HttpClient) {}
  onLogin() {
    console.log('Login attempt:', this.loginData);
    this.httpClient
      .post('http://localhost:8080/login', this.loginData, { observe: 'response' })
      .subscribe({
        next: (response) => {
          if (response.status === 200) {
            alert(`Login successful for user: ${this.loginData.userId}`);
            console.log('User data:', response.body);
          }
        },
        error: (err) => {
          if (err.status === 401) {
            alert('Invalid password. Please try again.');
          } else if (err.status === 404) {
            alert('User not found.');
          } else {
            alert('An unexpected error occurred.');
          }
        },
        complete: () => {
          console.log('Login API call completed.');
        },
      });
  }
}
