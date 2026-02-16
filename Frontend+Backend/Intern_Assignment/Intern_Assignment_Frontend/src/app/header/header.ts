import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

interface Section {
  path: string;
  name: string;
}

interface ActionSection {
  name: string;
  action: () => void;
}

@Component({
  selector: 'app-header',
  templateUrl: './header.html',
  styleUrls: ['./header.css'],
  standalone: true, 
  imports: [CommonModule, RouterModule],
})
export class HeaderComponent {
  headerSectionLeft: Section[] = [
    { path: '', name: 'Device Inventory System' }
  ];

  headerSectionMiddle: Section[] = [
    { path: '/home', name: 'Home' },
    { path: '/device/creation', name: 'Device Creation' },
    { path: '/device/summary', name: 'Device Summary' },
    { path: '/shelf/creation', name: 'Shelf Creation' },
    { path: '/shelf/summary', name: 'Shelf Summary' },
  ];

  headerSectionRightLoggedIn: ActionSection[] = [
    { name: 'Profile', action: () => this.goToProfile() },
    { name: 'Logout', action: () => this.logout() },
  ];

  headerSectionRightGuest: ActionSection[] = [
    { name: 'Login', action: () => this.login() },
    { name: 'Signup', action: () => this.signup() },
  ];

  isLoggedIn = false;
  dropdownOpen = false;

  toggleDropdown() {
    this.dropdownOpen = !this.dropdownOpen;
  }

  login() {
    this.isLoggedIn = true;
    console.log('Login clicked');
  }

  signup() {
    console.log('Signup clicked');
  }

  logout() {
    this.isLoggedIn = false;
    console.log('Logged out');
  }

  goToProfile() {
    console.log('Profile clicked');
  }
}
