import { Component, OnInit, ChangeDetectorRef, SimpleChanges, OnChanges } from "@angular/core";
import { DeviceModel } from "../models/DeviceModel";
import { DeviceService } from "../service/device-service";
import { RouterModule } from "@angular/router";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";

@Component({
  selector: 'app-device-summary-page',
  templateUrl:'./device-summary-page.html',
  styleUrls: ['./device-summary-page.css'],
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
})
export class DeviceSummaryPage implements OnInit, OnChanges {
  devices: DeviceModel[] = [];
  tableHeaders: string[] = [
    'ID',
    'Device Name',
    'Part Number',
    'Building Name',
    'Device Type',
    'Number of Shelf Positions',
    'Actions',
  ];

  constructor(private deviceService: DeviceService, private cdRef: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.loadDevices();
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.cdRef.detectChanges();
  }

  loadDevices(): void {
    this.deviceService.getAllDevices().subscribe({
      next: (data) => {
        this.devices = data;
        console.log('Devices loaded in summary page:', data);
        this.cdRef.detectChanges();
      },
      error: (err) => {
        console.error('Failed to load devices', err);
      }
    });
  }

  deleteDevice(id: string): void {
    this.deviceService.deleteDevice(id).subscribe({
      next: () => {
        this.devices = this.devices.filter(d => d.deviceId !== id);
        console.log(`Device ${id} deleted`);
        this.cdRef.detectChanges();
      },
      error: (err) => {
        console.error(`Failed to delete device ${id}`, err);
      }
    });
  }
}