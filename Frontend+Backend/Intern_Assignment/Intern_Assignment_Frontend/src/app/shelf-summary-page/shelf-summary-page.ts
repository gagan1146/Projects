import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

interface ShelfModel {
  id: string;
  shelfName: string;
  partNumber: number;
  buildingName: string;
  numberOfSlots: number;
}

@Component({
  selector: 'app-shelf-summary-page',
  imports: [FormsModule,CommonModule],
  templateUrl: './shelf-summary-page.html',
  styleUrl: './shelf-summary-page.css',
  standalone: true,
})
export class ShelfSummaryPage {
  shelves: ShelfModel[] = [
    {
      id: 'S001',
      shelfName: 'Shelf Alpha',
      partNumber: 7001,
      buildingName: 'Main HQ',
      numberOfSlots: 10,
    },
    {
      id: 'S002',
      shelfName: 'Shelf Beta',
      partNumber: 7002,
      buildingName: 'Data Center A',
      numberOfSlots: 8,
    },
    {
      id: 'S003',
      shelfName: 'Shelf Gamma',
      partNumber: 7003,
      buildingName: 'Office Tower',
      numberOfSlots: 12,
    },
  ];
  tableHeaders: string[] = [
    'ID',
    'Shelf Name',
    'Part Number',
    'Building Name',
    'Number of Slots',
    'Actions',
  ];
  newShelf: ShelfModel = {
    id: '',
    shelfName: '',
    partNumber: 0,
    buildingName: '',
    numberOfSlots: 0,
  };
  fxnCalled() {
    const newId = 'S' + (this.shelves.length + 1).toString().padStart(3, '0');
    this.shelves.push({ ...this.newShelf, id: newId });
    this.newShelf = { id: '', shelfName: '', partNumber: 0, buildingName: '', numberOfSlots: 0 };
    alert('Shelf added successfully!');
  }
  deleteShelf(id: string | undefined) {
    this.shelves = this.shelves.filter((s) => s.id !== id);
  }
  updateShelf(id: string | undefined) {
    const shelf = this.shelves.find((s) => s.id === id);
    if (shelf) {
      shelf.shelfName = shelf.shelfName + ' (Updated)';
    }
  }
}
