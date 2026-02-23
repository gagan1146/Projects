import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HeaderComponent } from '../header/header';
import { DeviceService } from './device-service';
import { DataForDropDown } from '../models/ShelfWithShelfModel';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ShelfPosiiton {
  private apiUrl = 'http://localhost:8080/api/shelfPosition';
  constructor(private httpClient: HttpClient) {}
  loadShelfWithShelfPosition(deviceId: string): Observable<DataForDropDown[]> {
    return this.httpClient.get<DataForDropDown[]>(`${this.apiUrl}/${deviceId}`);
  }
}
