import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { DeviceService } from '../service/device-service';
import { DeviceModel } from '../models/DeviceModel';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AddShelvesToDevice } from '../add-shelves-to-device/add-shelves-to-device';

@Component({
  selector: 'app-device-only-page',
  templateUrl: './device-only-page.html',
  styleUrls: ['./device-only-page.css'],
  imports:[FormsModule,CommonModule,AddShelvesToDevice],
  standalone:true
})
export class DeviceOnlyPage implements OnInit {
  device!: DeviceModel;

  constructor(
    private httpClient: HttpClient,
    private deviceService: DeviceService,
    private route: ActivatedRoute,
    private cdRef: ChangeDetectorRef,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('deviceId');
    console.log(id);
    
    if (id) {
      this.deviceService.getDeviceById(id).subscribe({
        next: (data) => {
          this.device = data;
          console.log('Loaded device:', data);
          this.cdRef.detectChanges();
        },
        error: (err) => {
          console.error('Failed to load device', err);
        }
      });
    }
  }

  fxnCalled(): void {
    if (!this.device || this.device.buildingName === '' || this.device.deviceName === '') {
      alert('Device data is incomplete, update aborted.');
      return;
    }

    this.deviceService.updateDevice(this.device.deviceId, this.device).subscribe({
      next: (updated) => {
        alert(`Updated device:', ${updated}`);
        this.router.navigate(["/device/summary"]);
      },
      error: (err) => {
        console.error('Failed to update device', err);
      }
    });
  }
}