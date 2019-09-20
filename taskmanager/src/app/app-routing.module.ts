import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddTaskComponent } from './ui/task/add/add.task.component';
import { ViewTaskComponent } from './ui/task/view/view.task.component';
import { EditTaskComponent } from './ui/task/edit/edit.task.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/viewtask',
    pathMatch: 'full'
     },
     {
       path: 'addtask',
      component: AddTaskComponent
     },
     {
      path: 'edittask/:id',
     component: EditTaskComponent
    },
     {
     path: 'viewtask',
     component: ViewTaskComponent
    }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
