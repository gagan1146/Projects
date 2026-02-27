import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ShelfService } from '../service/shelf-service';
import { HttpClient } from '@angular/common/http';
import { ShelfModel } from '../models/ShelfModel';

@Component({
  selector: 'app-shelf-creation-page',
  imports: [CommonModule, FormsModule],
  templateUrl: './shelf-creation-page.html',
  styleUrl: './shelf-creation-page.css',
  standalone: true,
})
export class ShelfCreationPage {
  id!: string;
  shelfPositionId!: string;
  constructor(
    private shelfService: ShelfService,
    private route: ActivatedRoute,
    private httpClient: HttpClient,
    private router: Router
  ) {}
  shelf: ShelfModel = {
    shelfId:"",
    shelfName:"",
    partNumber:"",
    flag:true
  }
  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('deviceId')!;
    this.shelfPositionId = this.route.snapshot.paramMap.get('shelfPositionId')!;
    this.shelf.shelfId = this.id;
  }
  createShelf() {
    console.log('Shelf added:', this.shelf);
    if (this.shelf.partNumber == '' || this.shelf.shelfName == '') {
      alert('Complete the details');
    } else {
      this.httpClient
        .post(
          `http://localhost:8080/api/shelf/create/${this.shelfPositionId}/${this.id}`,
          this.shelf,
        )
        .subscribe({
          next: (data) => {
            alert('Shelf Added Successfully...');
            this.router.navigate(["/shelf/summary"]);
          },
          error: (error) => {
            alert(`Error Occurred: ${error}`);
          },
        });
    }
  }
}
