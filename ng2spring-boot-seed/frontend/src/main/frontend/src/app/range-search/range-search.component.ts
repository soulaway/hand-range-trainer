import { Component, OnInit } from '@angular/core';
import { Router }            from '@angular/router';

import { Observable }        from 'rxjs/Observable';
import { Subject }           from 'rxjs/Subject';

// Observable class extensions
import 'rxjs/add/observable/of';

// Observable operators
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

import { Range }              from '../swagger/model/Range';
import { DefaultApi }         from '../swagger/api/DefaultApi';

@Component({
  selector: 'range-search',
  templateUrl: './range-search.component.html',
  styleUrls: [ './range-search.component.css' ],
  providers: [DefaultApi]
})
export class RangeSearchComponent implements OnInit {
  ranges: Observable<Range[]>;
  private searchTerms = new Subject<string>();

  constructor(
    private rangeService: DefaultApi,
    private router: Router) {}

  // Push a search term into the observable stream.
  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.rangeService.defaultHeaders.append("Content-Type", 'application/json;charset=UTF-8');
    this.ranges = this.searchTerms
      .debounceTime(300)        // wait 300ms after each keystroke before considering the term
      .distinctUntilChanged()   // ignore if next search term is same as previous
      .switchMap(term => term   // switch to new observable each time the term changes
        // return the http search observable
        ? this.rangeService.findRanges(term)
        // or the observable of empty ranges if there was no search term
        : Observable.of<Range[]>([]))
      .catch(error => {
        // TODO: add real error handling
        console.log(error);
        return Observable.of<Range[]>([]);
      });
  }

  gotoDetail(range: Range): void {
    let link = ['/detail', range.rangeId];
    this.router.navigate(link);
  }
}
