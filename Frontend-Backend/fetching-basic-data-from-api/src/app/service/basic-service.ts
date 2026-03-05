import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GenerateList } from '../generate-list/generate-list';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class BasicService {
  private apiUrl = "http://localhost:8080"
  constructor(private httpClient:HttpClient){}
  list: number[] = [];
  GenerateList():Observable<any[]>{
    return this.httpClient.get<any[]>('[this.apiUrl,"generateList"]')
  }
}
