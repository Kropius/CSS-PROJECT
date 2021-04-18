import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpressionCalculatorComponent } from './expression-calculator.component';

describe('ExpressionCalculatorComponent', () => {
  let component: ExpressionCalculatorComponent;
  let fixture: ComponentFixture<ExpressionCalculatorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExpressionCalculatorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExpressionCalculatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
