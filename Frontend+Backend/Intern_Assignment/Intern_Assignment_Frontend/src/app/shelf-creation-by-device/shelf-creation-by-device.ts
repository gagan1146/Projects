import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, OnChanges, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ShelfService } from '../service/shelf-service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { ShelfModel } from '../models/ShelfModel';
import { DeviceOnlyIds } from '../models/DeviceOnlyIds';
import { DeviceService } from '../service/device-service';
import { ShelfPosiiton } from '../service/shelf-posiiton';

@Component({
  selector: 'app-shelf-creation-by-device',
  imports: [CommonModule, FormsModule],
  templateUrl: './shelf-creation-by-device.html',
  styleUrl: './shelf-creation-by-device.css',
  standalone: true
})
export class ShelfCreationByDevice implements OnInit,OnChanges {
  id!: string;
  shelfPositionId!: string;
  deviceOnlyIds: DeviceOnlyIds[] = [ { shelfPositonId: "" } ]; 
  constructor(
    private shelfService: ShelfService,
    private route: ActivatedRoute,
    private router: Router,
    private deviceService: DeviceService,
    private cdRef:ChangeDetectorRef,
    private shelfPositionService:ShelfPosiiton
  ) {}
  shelf: ShelfModel = {
    shelfId:"",
    shelfName:"",
    partNumber:"",
    flag:true
  }
  ngOnInit(): void {
    this.getAllShelf();
  }

  ngOnChanges(){

  }

  getAllShelf(): void {
    this.shelfPositionService.getAddDevicesById().subscribe({
      next: (data) => {
        this.shelfPositionService.deviceAndShelfPositionIds = data;
        console.log(data);
        
        this.cdRef.detectChanges();
      },
      error: (err) => {
        console.error('Failed to load devices', err);
      }
    });
  }
  createShelf() {
    // shelvePositions = this.httpClient.get()
  }
}
