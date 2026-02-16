import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-shelf-creation-page',
  imports: [CommonModule, FormsModule],
  templateUrl: './shelf-creation-page.html',
  styleUrl: './shelf-creation-page.css',
  standalone: true,
})
export class ShelfCreationPage {
  device = { deviceName: '', partNumber: '', buildingName: '', deviceType: '', numberOfShelf: '' }; fxnCalled() { console.log('Device added:', this.device); alert(`Device ${this.device.deviceName} added successfully!`); }

}
