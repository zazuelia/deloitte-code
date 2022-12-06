import { Component, Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { User } from '../global/user-interface';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Tasks } from '../tasks/local-interfaces/tasks-interface';
import { map } from 'rxjs/operators';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServicesComponent {

  constructor(
    private http: HttpClient,
    private fb: FormBuilder
  ) { }

  body = {};
  user: User = {
    userId: 1,
    userName: '',
    tasks: []
  }

  

  ngOnInit(): void {
  }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  authenticate(userName: string, password: string) {

    const url = 'http://localhost:8383/login';

    let loginForm = new URLSearchParams();
    loginForm.set('userName', userName);
    loginForm.set('password', password);

    let options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded'
      })
    };

    // return this.http.post(url, loginForm.toString(), options);

    return of({success: true, token: ""})

  }

  getTasksByUserName(userName: string) {

    const url = `http://localhost:8383/api/todo-task/get-tasks/${userName}`;    

    return this.http.get(url);

  }

  deleteTask(taskId: number) {

    const url = `http://localhost:8383/api/todo-task/delete-task/${taskId}`;

    return this.http.delete(url);
  }

  saveTask(task: Tasks) {

    let userName = localStorage.getItem('userName');
    this.user.userName = userName;
    task.user = this.user;
    const url = 'http://localhost:8383/api/todo-task/add-tasks';

    return this.http.post(url, task).pipe(
      map((ret) => ret as Tasks)
    );
  }

  checkTask(task: Tasks) {

    let userName = localStorage.getItem('userName');
    this.user.userName = userName;
    task.user = this.user;
    const url = 'http://localhost:8383/api/todo-task/check-task';

    return this.http.patch(url, task).pipe(
      map((ret) => ret as Tasks)
    );
  }

}
