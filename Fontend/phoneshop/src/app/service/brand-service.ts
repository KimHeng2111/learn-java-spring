import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Brand } from '../component/brand/brand';

const baseUrl = 'http://localhost:8080/brands';

@Injectable({
  providedIn: 'root',
})
export class BrandService {
  constructor(private http: HttpClient) {}

  saveBrand(brand: Brand): Observable<Brand> {
    // ✅ return Observable
    return this.http.post<Brand>(baseUrl, brand);
  }
}
