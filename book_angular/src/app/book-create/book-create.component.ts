import { Component, OnInit } from '@angular/core';
import {Book} from "../book";
import {BookService} from "../book.service";
import {Router} from "@angular/router";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'app-book-create',
  templateUrl: './book-create.component.html',
  styleUrls: ['./book-create.component.css']
})
export class BookCreateComponent implements OnInit {

  book: Book = new Book();
  constructor(private bookservice: BookService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.saveBook();
  }

  saveBook(){
    this.bookservice.createBook(this.book).subscribe(data=>{
        console.log(data)
        this.backToBookList();
      })

  }

  backToBookList(){
    this.router.navigate(["/book-list"])
  }


}
