import { Component}          from '@angular/core';
import { DefaultApi }          from './swagger/api/DefaultApi';
@Component({
  selector: 'app-root',
  template: `
    <h1>{{title}}</h1>
    <nav>
      <a routerLink="/dashboard" routerLinkActive="active">Dashboard</a>
      <a routerLink="/ranges" routerLinkActive="active">Ranges</a>
    </nav>
    <router-outlet></router-outlet>
  `,
  styleUrls: ['./app.component.css']
})
export class AppComponent{
  title = 'Hand Range Trainer';
}
