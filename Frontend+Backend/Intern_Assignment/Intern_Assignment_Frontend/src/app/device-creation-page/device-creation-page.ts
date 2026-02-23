import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { DeviceModel } from '../models/DeviceModel';
import { DeviceService } from '../service/device-service';

@Component({
  selector: 'app-device-creation-page',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './device-creation-page.html',
  styleUrls: ['./device-creation-page.css']
})
export class DeviceCreationPage {
  constructor(private httpClient: HttpClient, private deviceService:DeviceService) {}

  device = { 
    deviceName: '', 
    partNumber: '', 
    buildingName: '', 
    deviceType: '', 
    numberOfShelfPositions: 0,
    flag:true
  };

  createDevice() {
    if (this.device.buildingName === '' || this.device.deviceName === '') {
      return;
    }
    console.log(this.device);
    
    this.httpClient.post('http://localhost:8080/api/devices/create', this.device)
      .subscribe(data => {
        console.log('Created device:', data);
      });
  }
}