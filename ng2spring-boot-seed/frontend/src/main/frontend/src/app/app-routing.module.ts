import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent }   from './dashboard/dashboard.component';
import { RangesComponent }      from './ranges/ranges.component';
import { RangeDetailComponent }  from './range-detail/range-detail.component';
import { HandDetailComponent }  from './hand/hand-detail.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard',      component: DashboardComponent },
  { path: 'hand/:rangeId',  component: HandDetailComponent },
  { path: 'detail/:id',     component: RangeDetailComponent },
  { path: 'ranges',         component: RangesComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
