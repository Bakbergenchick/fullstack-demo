import { Component, OnInit } from '@angular/core';
import {BookService} from "../book.service";
import {Book} from "../book";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-book-update',
  templateUrl: './book-update.component.html',
  styleUrls: ['./book-update.component.css']
})
export class BookUpdateComponent implements OnInit {

  id: number
  book: Book = new Book();
  constructor(private bookservice: BookService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id']
    this.bookservice.getBook(this.id).subscribe(data=>{
      console.log(data)
      this.book = data;
    })
  }


  onSubmit() {
    this.bookservice.editBook(this.book).subscribe(data => {
      this.backToBookList()
    })
  }

  backToBookList(){
    this.router.navigate(['/book-list'])
  }
}
