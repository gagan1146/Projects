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
  standalone: true,
})
export class ShelfCreationByDevice implements OnInit, OnChanges {
  id!: string;
  shelfPositionId!: string;
  deviceOnlyIds: DeviceOnlyIds[] = [];
  deviceAndShelfPositionIds: DeviceOnlyIds[] = [];
  constructor(
    private shelfService: ShelfService,
    private route: ActivatedRoute,
    private router: Router,
    private deviceService: DeviceService,
    private cdRef: ChangeDetectorRef,
    private shelfPositionService: ShelfPosiiton,
    private httpClient: HttpClient,
  ) {}
  shelf: ShelfModel = {
    shelfId: '',
    shelfName: '',
    partNumber: '',
    flag: true,
  };
  ngOnInit(): void {
    this.getAllShelf();
  }

  ngOnChanges() {}

  getAllShelf(): void {
    this.shelfPositionService.getAddDevicesById().subscribe({
      next: (data) => {
        this.deviceAndShelfPositionIds = data;
        this.deviceAndShelfPositionIds.sort((a, b) =>
        a.shelfPositionId.localeCompare(b.shelfPositionId));
        this.shelfPositionService.deviceAndShelfPositionIds = this.deviceAndShelfPositionIds;
        console.log(data);
        this.cdRef.detectChanges();
      },
      error: (err) => {
        console.error('Failed to load devices', err);
      },
    });
  }
  createShelf() {
    if (this.shelfPositionId === undefined || typeof this.shelfPositionId === 'undefined') {
      alert(`Select the ShelfPositionId...`);
    } else {
      console.log('Shelf added:', this.shelf);
      console.log(this.shelfPositionId);

      if (this.shelf.partNumber == '' || this.shelf.shelfName == '') {
        alert('Complete the details');
      } else {
        console.log(this.shelfPositionId);
        if (this.shelfPositionId === undefined || typeof this.shelfPositionId === 'undefined') {
          alert(`Select the ShelfPositionId...`);
        }
        this.httpClient
          .post(`http://localhost:8080/api/shelf/create/${this.shelfPositionId}`, this.shelf)
          .subscribe({
            next: (data) => {
              alert('Shelf Added Successfully...');
              this.router.navigate(['/shelf/summary']);
            },
            error: (error) => {
              alert(`Error Occurred: ${error}`);
            },
          });
      }
    }
  }
}
