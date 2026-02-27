import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-routing-page',
  imports: [],
  templateUrl: './routing-page.html',
  styleUrl: './routing-page.css',
})
export class RoutingPage {
  constructor(private router:Router){}
  ngOnInit(){
    this.router.navigate(['/shelf/summary']);
  }
}
