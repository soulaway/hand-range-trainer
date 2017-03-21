import { Component, OnInit } from '@angular/core';
import { Router }            from '@angular/router';
//import { Jsonp, URLSearchParams } from '@angular/http';
import { Hero }                from '../model/hero';
import { HeroService }         from '../service/hero.service';

import { Range }              from '../swagger/model/Range';
import { DefaultApi }         from '../swagger/api/DefaultApi';

@Component({
  selector: 'my-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: [ './heroes.component.css' ]
})
export class HeroesComponent implements OnInit {
  
  heroes: Hero[];
  selectedHero: Hero;

  //rangesOble$: Observable<Range[]>;  
  //rangesObserver: Observer<Range[]>;
  ranges: Range[];
  selRange: Range;
  
  constructor(
    private heroService: HeroService,
    private rangeService: DefaultApi,
    private router: Router) { 
  }

  getHeroes(): void {
    console.log('get ranges: ');
    this.rangeService.findRanges().subscribe(
    	val => console.log(val)
    );
//    this.heroService
//        .getHeroes()
//        .then(heroes => this.heroes = heroes);
  }

  add(name: string): void {
    name = name.trim();
    if (!name) { return; }
    this.rangeService.addRange(name).subscribe(
    	val => console.log(val)
    );
    //this.heroService.create(name)
    //  .then(hero => {
    //    this.heroes.push(hero);
    //    this.selectedHero = null;
    //  });
  }

  delete(hero: Hero): void {
    this.heroService
        .delete(hero.id)
        .then(() => {
          this.heroes = this.heroes.filter(h => h !== hero);
          if (this.selectedHero === hero) { this.selectedHero = null; }
        });
  }

  ngOnInit(): void {
    this.getHeroes();
  }

  onSelect(hero: Hero): void {
    this.selectedHero = hero;
  }

  gotoDetail(): void {
    this.router.navigate(['/detail', this.selectedHero.id]);
  }
}
