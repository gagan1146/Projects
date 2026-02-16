import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-device-creation-page',
  standalone: true, 
  imports: [CommonModule, FormsModule], 
  templateUrl: './device-creation-page.html', 
  styleUrls: ['./device-creation-page.css']
})
export class DeviceCreationPage {
  device = { deviceName: '', partNumber: '', buildingName: '', deviceType: '', numberOfShelf: '' }; fxnCalled() { console.log('Device added:', this.device); alert(`Device ${this.device.deviceName} added successfully!`); }
}
