import { ChangeDetectorRef, Component, NgModule } from '@angular/core';
import { BasicService } from '../service/basic-service';

@Component({
  selector: 'app-generate-list',
  imports: [NgModule],
  templateUrl: './generate-list.html',
  styleUrl: './generate-list.css',
  standalone:true
})
export class GenerateList {
  constructor(private basicService:BasicService, private cdRef:ChangeDetectorRef){}
  list: number[] = [];
  ngOnInit() {
    this.basicService.GenerateList().subscribe((response) => {
      this.list = response;
      console.log(this.list);
    });
    this.cdRef.detectChanges();
  }
}