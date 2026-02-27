import { Component, OnChanges, OnInit, SimpleChanges, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ShelfService } from '../service/shelf-service';
import { ShelfModel } from '../models/ShelfModel';

@Component({
  selector: 'app-shelf-only-page',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './shelf-only-page.html',
  styleUrls: ['./shelf-only-page.css'],
})
export class ShelfOnlyPage implements OnInit, OnChanges {
  shelfId: string = '';
  shelfData: ShelfModel[] = [
    {
      shelfId: '',
      shelfName: 'shelfName',
      partNumber: 'PartNumber',
      flag: false,
    },
  ];
  shelf: ShelfModel = { shelfName: 'shelfName', partNumber: 'partNumber', shelfId: '', flag: true };

  constructor(
    private shelfService: ShelfService,
    private route: ActivatedRoute,
    private cdRef: ChangeDetectorRef,
    private router: Router
  ) {}

  ngOnChanges(changes: SimpleChanges): void {
    this.cdRef.detectChanges();
  }

  ngOnInit() {
    this.shelfId = this.route.snapshot.paramMap.get('shelfPositionId')!;
    console.log('Route param shelfId:', this.shelfId);
    this.shelf.shelfId = this.shelfId;
    if (this.shelfId) {
      this.shelfService.getShelfById(this.shelfId).subscribe({
        next: (data) => {
          console.log('Fetched shelf data:', data);
          this.shelf = data;
          this.shelf.shelfName = data.shelfName;
          this.shelf.partNumber = data.partNumber;
          this.cdRef.detectChanges();
        },
        error: (error) => {
          console.error(error);
          alert(error);
        },
      });
    }
  }

  updateShelf(): void {
    console.log(this.shelfId,this.shelf);
    
    if (!this.shelf.shelfName || !this.shelf.partNumber) {
      console.warn('Form is incomplete, update aborted.');
      return;
    }
    if (this.shelfId) {
      this.shelfService.updateShelfById(this.shelfId, this.shelf).subscribe({
        next: (data) => {
          console.log('Shelf updated successfully:', data);
          alert('Shelf updated successfully!');
          this.router.navigate(['/shelf/summary']);
        },
        error: (error) => {
          console.error(error);
          alert(`Error Occurred: ${error}`);
        },
      });
    }
  }

  

}
