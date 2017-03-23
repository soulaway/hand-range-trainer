import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { HttpModule }    from '@angular/http';

import { AppRoutingModule } from './app-routing.module';

// Imports for loading & configuring the in-memory web api
//import { InMemoryWebApiModule } from 'angular-in-memory-web-api';
//import { InMemoryDataService }  from './repository/in-memory-data.service';

import { AppComponent }         from './app.component';
import { DashboardComponent }   from './dashboard/dashboard.component';
import { RangesComponent }      from './ranges/ranges.component';
import { RangeDetailComponent }  from './range-detail/range-detail.component';
import { DefaultApi }          from './swagger/api/DefaultApi';
import { RangeSearchComponent }  from './range-search/range-search.component';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    RangeDetailComponent,
    RangesComponent,
    RangeSearchComponent
  ],
  providers: [ DefaultApi ],
  bootstrap: [ AppComponent ]
})
export class AppModule {
    
}
