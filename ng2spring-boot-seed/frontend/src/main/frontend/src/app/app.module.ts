import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { HttpModule }    from '@angular/http';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent }         from './app.component';
import { DashboardComponent }   from './dashboard/dashboard.component';
import { RangesComponent }      from './ranges/ranges.component';
import { RangeDetailComponent }  from './range-detail/range-detail.component';
import { HandDetailComponent } from './hand/hand-detail.component';
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
    HandDetailComponent,
    RangesComponent,
    RangeSearchComponent
  ],
  providers: [ DefaultApi ],
  bootstrap: [ AppComponent ]
})
export class AppModule {
    
}
