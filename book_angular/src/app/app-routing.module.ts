import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {BookListComponent} from "./book-list/book-list.component";
import {BookCreateComponent} from "./book-create/book-create.component";
import {BookUpdateComponent} from "./book-update/book-update.component";
import {BookDetailsComponent} from "./book-details/book-details.component";

const routes: Routes = [
  {path: "book-list", component: BookListComponent},
  {path: "create-book", component: BookCreateComponent},
  {path: "update-book/:id", component: BookUpdateComponent},
  {path: "book-details/:id", component: BookDetailsComponent},
  {path: '', redirectTo: 'book-list', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
