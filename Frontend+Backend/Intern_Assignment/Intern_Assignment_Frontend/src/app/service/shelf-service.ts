import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ShelfModel } from '../models/ShelfModel';
import { ShelfWithDeviceAndShelfPosition } from '../models/ShelfWithDeviceAndShelfPosition';

@Injectable({
  providedIn: 'root',
})
export class ShelfService {
  private apiUrl = 'http://localhost:8080/api/shelf';

  constructor(private httpClient: HttpClient) {}

  getAllShelves(): Observable<ShelfWithDeviceAndShelfPosition[]> {
    return this.httpClient.get<ShelfWithDeviceAndShelfPosition[]>(this.apiUrl);
  }

  getShelfById(id: string): Observable<ShelfModel> {
    return this.httpClient.get<ShelfModel>(`${this.apiUrl}/${id}`);
  }

  updateShelfById(id: string, shelf: ShelfModel): Observable<ShelfModel> {
    return this.httpClient.put<ShelfModel>(`${this.apiUrl}/update/${id}`, shelf);
  }

  createShelf(shelfPositionId: string, deviceId: string, shelf: { shelfName: string; partNumber: number }): Observable<ShelfModel> {
    return this.httpClient.post<ShelfModel>(`${this.apiUrl}/create/${shelfPositionId}/${deviceId}`, shelf);
  }

  deleteShelfById(id:string){
    return this.httpClient.delete(this.apiUrl + '/' + id);
  }

}