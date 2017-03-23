import { Component, OnInit } from '@angular/core';

import { Range }              from '../swagger/model/Range';
import { DefaultApi }         from '../swagger/api/DefaultApi';

@Component({
  selector: 'my-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
  ranges: Range[] = [];

  constructor(private rangeService: DefaultApi) { }

  ngOnInit(): void {
      this.rangeService.defaultHeaders.append("Content-Type", 'application/json;charset=UTF-8');
      this.rangeService.findRanges().subscribe(
       ranges => this.ranges = ranges.slice(1, 5)
    );
  }
}
