import { Component } from '@angular/core';

@Component({
  selector: 'app-main',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'calculator';
  addition = "Add"
  substraction = "Minus"
  power = "Power"
  division = "Division"
  multiplication = "Multiply"
  root = "Square root"


}
