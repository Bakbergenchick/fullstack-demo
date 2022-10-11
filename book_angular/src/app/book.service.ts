import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Book} from "./book";

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private baseURL = "http://localhost:8080/api/books";

  constructor(private http: HttpClient) { }

  getBooks(): Observable<Book[]>{

    return this.http.get<Book[]>(`${this.baseURL}`);
  }

  getBook(id: number): Observable<Book>{

    return this.http.get<Book>(`${this.baseURL}/${id}`);
  }

  createBook(book: Book): Observable<Object>{
    return this.http.post(`${this.baseURL}`, book);
  }

  editBook(book: Book): Observable<Object>{
    return this.http.put(`${this.baseURL}`, book)
  }

  deleteBook(bookID: number): Observable<Object>{
    return this.http.delete(`${this.baseURL}/${bookID}`);
  }

}
