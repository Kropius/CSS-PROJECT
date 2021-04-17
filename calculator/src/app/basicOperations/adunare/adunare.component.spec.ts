import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdunareComponent } from './adunare.component';

describe('AdunareComponent', () => {
  let component: AdunareComponent;
  let fixture: ComponentFixture<AdunareComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdunareComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdunareComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
