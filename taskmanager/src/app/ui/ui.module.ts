import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { LayoutComponent } from './layout/layout.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { FormsModule, FormBuilder } from '@angular/forms';
import { AppRoutingModule } from '../app-routing.module';
import { AddTaskComponent } from './task/add/add.task.component';
import { ViewTaskComponent } from './task/view/view.task.component';
import { EditTaskComponent } from './task/edit/edit.task.component';
import { FilterTasksPipe } from '../util/filter-tasks.pipe';
import { HttpClientModule, HttpClient } from '@angular/common/http';


@NgModule({
  declarations: [
      LayoutComponent,
      HeaderComponent,
      FooterComponent,
      AddTaskComponent,
      ViewTaskComponent,
      EditTaskComponent,
      FilterTasksPipe
       ],

  imports: [
    CommonModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
    
  ],
  providers: [
    DatePipe, FormBuilder
  ]
,  exports: [
    LayoutComponent
]
})
export class UiModule { }
