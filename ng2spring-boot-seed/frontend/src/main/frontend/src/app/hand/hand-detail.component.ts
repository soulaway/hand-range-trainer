import 'rxjs/add/operator/switchMap';
import { Component, OnInit }      from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Location }               from '@angular/common';

import { Hand }               from '../swagger/model/Hand';
import { RangeStat }          from '../swagger/model/RangeStat';
import { DefaultApi }         from '../swagger/api/DefaultApi';

@Component({
  selector: 'my-hand-detail',
  templateUrl: './hand-detail.component.html',
  styleUrls: [ './hand-detail.component.css' ]
})
export class HandDetailComponent implements OnInit {
  hand: Hand;
  stat: RangeStat;

  constructor(
    private rangeService: DefaultApi,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
      this.rangeService.defaultHeaders.append("Content-Type", 'application/json;charset=UTF-8');
      this.route.params
      .switchMap((params: Params) => this.rangeService.genHand(+params['rangeId']))
      .subscribe(hand => this.hand = hand);
  }

  answer(ans: number): void {
    this.hand.answer = ans;
      this.rangeService.solveHand(this.hand).subscribe(
      stat => {
          this.stat = stat;
          this.route.params
          .switchMap((params: Params) => this.rangeService.genHand(+params['rangeId']))
          .subscribe(hand => this.hand = hand);
      }
    );
  }

  goBack(): void {
    this.location.back();
  }
}
