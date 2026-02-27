import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HeaderComponent } from '../header/header';
import { DeviceService } from './device-service';
import { DataForDropDown } from '../models/ShelfWithShelfModel';
import { Observable } from 'rxjs';
import { DeviceOnlyIds } from '../models/DeviceOnlyIds';

@Injectable({
  providedIn: 'root',
})
export class ShelfPosiiton {

  deviceAndShelfPositionIds : DeviceOnlyIds[]  = [{
    shelfPositonId: ""
  }];
  private apiUrl = 'http://localhost:8080/api/shelfPosition';
  constructor(private httpClient: HttpClient) {}
  loadShelfWithShelfPosition(deviceId: string): Observable<DataForDropDown[]> {
    return this.httpClient.get<DataForDropDown[]>(`${this.apiUrl}/${deviceId}`);
  }

  getAddDevicesById(): Observable<DeviceOnlyIds[]> {
    return this.httpClient.get<DeviceOnlyIds[]>(`${this.apiUrl}/shelfPositionIds`);
  }




  // getAllShelfPositions(): Observable<ShelfPosiiton[]> {
  //   return this.httpClient.get<>(`${this.apiUrl}/`);
  // }
}
