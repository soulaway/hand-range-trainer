import { Component, OnInit } from '@angular/core';
import { Router }            from '@angular/router';
//import { Jsonp, URLSearchParams } from '@angular/http';

import { Range }              from '../swagger/model/Range';
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
    name = name.trim();
    if (!name) { return; }
    this.rangeService.addRange(name).subscribe(
      range => {
        this.ranges.push(range);
        this.selectedRange = null;
      }
    );
  }

  delete(range: Range): void {
    this.rangeService.deleteRange(range.rangeId).subscribe(
        () => {
          this.ranges = this.ranges.filter(h => h !== range);
          if (this.selectedRange === range) { this.selectedRange = null; }
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
}
