import { Component, OnInit } from '@angular/core';
import {Book} from "../book";
import {BookService} from "../book.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-details-book',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  public books: Book[];

  constructor(private bookservice: BookService, private router: Router) {
  }

  ngOnInit(): void {
    this.getBooks();
  }

  getBooks(){
    this.bookservice.getBooks().subscribe(data => {
      console.log(data)
      this.books = data
    })
  }

  editBook(id: number) {
    this.router.navigate(['update-book', id] )
  }

  deleteBook(id: number) {
    this.bookservice.deleteBook(id).subscribe(data=>{
      this.getBooks();
    })
  }

  detailsOfBook(id: number) {
    this.router.navigate(['book-details', id])
  }
}
