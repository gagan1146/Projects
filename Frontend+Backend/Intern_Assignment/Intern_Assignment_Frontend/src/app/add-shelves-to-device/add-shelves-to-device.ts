import { Component, Input, Output, EventEmitter, OnChanges, SimpleChanges, ChangeDetectorRef, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { DataForDropDown } from '../models/ShelfWithShelfModel';
import { ShelfPosiiton } from '../service/shelf-posiiton';

@Component({
  selector: 'app-add-shelves-to-device',
  templateUrl: './add-shelves-to-device.html',
  styleUrls: ['./add-shelves-to-device.css'],
  imports: [FormsModule, CommonModule, RouterModule],
  standalone: true
})
export class AddShelvesToDevice implements OnChanges,OnInit {
  @Input() id!: string;   
  @Output() shelfAdded = new EventEmitter<DataForDropDown>(); 

  dataForDropDown: DataForDropDown[] = [];

  constructor(private shelfPosiiton: ShelfPosiiton, private cdRef: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.loadShelfWithShelfPosition();
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.cdRef.detectChanges();
  }

  loadShelfWithShelfPosition(): void {
    this.shelfPosiiton.loadShelfWithShelfPosition(this.id).subscribe({
      next: (data: DataForDropDown[]) => {
        this.dataForDropDown = data;
        console.log('Shelves loaded:', this.dataForDropDown);
        this.cdRef.detectChanges();
      },
      error: (err) => {
        console.error('Failed to load shelves', err);
      }
    });
  }

  onAdd(item: DataForDropDown): void {
    console.log('Adding shelf position:', item.shelfPositionId);
    this.shelfAdded.emit(item);
    this.cdRef.detectChanges();
  }

  trackByShelfPositionId(index: number, item: DataForDropDown): string {
    return item.shelfPositionId;
  }
}