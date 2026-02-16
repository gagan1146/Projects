import { Component } from '@angular/core';
import { DeviceModel } from '../models/DeviceModel';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-device-summary-page',
  templateUrl: './device-summary-page.html',
  styleUrls: ['./device-summary-page.css'],
  standalone: true, 
  imports: [CommonModule, RouterModule],
})
export class DeviceSummaryPage {
  devices: DeviceModel[] = [
    { id: 'D001', deviceName: 'Core Router', partNumber: 1001, BuildingName: 'Main HQ', DeviceType: 'Networking', numberOfShelf: 4 },
    { id: 'D002', deviceName: 'Edge Switch', partNumber: 2002, BuildingName: 'Data Center A', DeviceType: 'Switching', numberOfShelf: 2 },
    { id: 'D003', deviceName: 'Firewall Appliance', partNumber: 3003, BuildingName: 'Security Block', DeviceType: 'Security', numberOfShelf: 1 },
    { id: 'D004', deviceName: 'Storage Server', partNumber: 4004, BuildingName: 'Data Center B', DeviceType: 'Storage', numberOfShelf: 6 },
    { id: 'D005', deviceName: 'Wireless Controller', partNumber: 5005, BuildingName: 'Office Tower', DeviceType: 'Wireless', numberOfShelf: 3 }
  ];

  tableHeaders: string[] = [
    'ID',
    'Device Name',
    'Part Number',
    'Building Name',
    'Device Type',
    'Number of Shelf Positions',
    'Actions'
  ];

  deleteDevice(id: string | undefined) {
    this.devices = this.devices.filter(d => d.id !== id);
  }

  updateDevice(id: string | undefined) {
    const device = this.devices.find(d => d.id === id);
    if (device) {
      device.deviceName = device.deviceName + ' (Updated)';
    }
  }
}