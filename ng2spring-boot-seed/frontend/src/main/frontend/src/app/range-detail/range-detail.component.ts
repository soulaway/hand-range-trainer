import 'rxjs/add/operator/switchMap';
import { Component, OnInit }      from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Location }               from '@angular/common';

import { Range }              from '../swagger/model/Range';
import { DefaultApi }         from '../swagger/api/DefaultApi';

@Component({
  selector: 'my-range-detail',
  templateUrl: './range-detail.component.html',
  styleUrls: [ './range-detail.component.css' ]
})
export class RangeDetailComponent implements OnInit {
  range: Range;

  constructor(
    private rangeService: DefaultApi,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
      this.rangeService.defaultHeaders.append("Content-Type", 'application/json;charset=UTF-8');
      this.route.params
      .switchMap((params: Params) => this.rangeService.findRangeById(+params['id']))
      .subscribe(range => this.range = range);
  }

  save(): void {
    this.rangeService.updateRange(this.range).subscribe(
      () => this.goBack())
    ;
  }

  goBack(): void {
    this.location.back();
  }
}
