import { Injectable } from '@angular/core';
import { DeviceModel } from '../models/DeviceModel';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DeviceOnlyIds } from '../models/DeviceOnlyIds';

@Injectable({
  providedIn: 'root',
})
export class DeviceService {
  private apiUrl = 'http://localhost:8080/api/devices';

  constructor(private httpClient: HttpClient) {}

  getAllDevices(): Observable<DeviceModel[]> {
    return this.httpClient.get<DeviceModel[]>(this.apiUrl);
  }

  getDeviceById(id: string): Observable<DeviceModel> {
    return this.httpClient.get<DeviceModel>(`${this.apiUrl}/${id}`);
  }

  updateDevice(id: string, device: DeviceModel): Observable<DeviceModel> {
    console.log(device);
    
    return this.httpClient.put<DeviceModel>(`${this.apiUrl}/${id}`, device);
  }

  deleteDevice(id: string): Observable<void> {
    return this.httpClient.delete<void>(`${this.apiUrl}/${id}`);
  }

  

  device = { 
    deviceName: '', 
    partNumber: '', 
    buildingName: '', 
    deviceType: '', 
    numberOfShelfPositions: 0,
    flag:true
  };

  
}