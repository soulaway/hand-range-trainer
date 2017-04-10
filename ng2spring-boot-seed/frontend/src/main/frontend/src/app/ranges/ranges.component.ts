import { Component, OnInit } from '@angular/core';
import { Router }            from '@angular/router';

import { Range }              from '../swagger/model/Range';
import { RangeType }              from '../swagger/model/RangeType';
import { DefaultApi }         from '../swagger/api/DefaultApi';

@Component({
  selector: 'my-ranges',
  templateUrl: './ranges.component.html',
  styleUrls: [ './ranges.component.css' ]
})
export class RangesComponent implements OnInit {
  
  ranges: Range[];
  selectedRange: Range;

  constructor(
    private rangeService: DefaultApi,
    private router: Router) { 
  }

  getRanges(): void {
    this.rangeService.findRanges().subscribe(
      ranges => this.ranges = ranges
    );
  }

  add(name: string): void {
    //name = name.trim();
    if (!name) { return; }
    console.log(name);
    this.rangeService.addRange(name, RangeType._1a).subscribe(
      range => {
        this.ranges.push(range);
        this.selectedRange = null;
      }
    );
  }

  delete(range: Range): void {
    this.rangeService.deleteRange(range.rangeId).subscribe(
        r => {
            var index = this.ranges.indexOf(range, 0);
            if (index > -1) {
                this.selectedRange = null
                this.ranges.splice(index, 1);
            }
        }
     );
  }

  ngOnInit(): void {
    this.rangeService.defaultHeaders.append("Content-Type", 'application/json;charset=UTF-8');
    this.getRanges();
  }

  onSelect(range: Range): void {
    this.selectedRange = range;
  }

  gotoDetail(): void {
    this.router.navigate(['/detail', this.selectedRange.rangeId]);
  }
  
  getHand(): void {
    this.router.navigate(['/hand', this.selectedRange.rangeId]);
  }
}
