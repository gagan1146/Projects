import { Component, OnInit, OnChanges, ChangeDetectorRef, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ShelfService } from '../service/shelf-service';
import { ShelfModel } from '../models/ShelfModel';
import { ShelfWithDeviceAndShelfPosition } from '../models/ShelfWithDeviceAndShelfPosition';

@Component({
  selector: 'app-shelf-summary-page',
  imports: [FormsModule,CommonModule,RouterModule],
  templateUrl: './shelf-summary-page.html',
  styleUrl: './shelf-summary-page.css',
  standalone: true,
})
export class ShelfSummaryPage implements OnInit,OnChanges {
  tableHeaders: string[] = [
    'SHELF ID',
    'Shelf Name',
    'Part Number',
    'Shelf Position',
    'Device Name',
    'Actions',
  ];
  shelves: ShelfWithDeviceAndShelfPosition[] = [];
  constructor(private shelfService: ShelfService, private cdRef: ChangeDetectorRef) {}
  ngOnInit(): void {
    this.loadShelves();
  }
  ngOnChanges(changes: SimpleChanges): void {
    this.cdRef.detectChanges();
  }
  
  loadShelves(){
    this.shelfService.getAllShelves().subscribe({
      next: (data) => {
        this.shelves = data;
        console.log('Shelves loaded in summary page:', data);
        this.cdRef.detectChanges();
      },
      error: (err) => {
        console.error('Failed to load shelves', err);
      }
    });
  }
  deleteShelfById(id:string){
    this.shelfService.deleteShelfById(id).subscribe({
      next:
      (data)=>{
        console.log(data);
      },
      error:
      (error)=>{
        console.log(error);
        
      }
    })
  }
  updateShelf(shelfId: string | undefined) {
  }
  trackByShelfId(index: number, item: ShelfWithDeviceAndShelfPosition): string {
  return item.shelfId;
}
}
