import {Component, OnInit} from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {HttpErrorResponse} from "@angular/common/http";
import {OperationService} from "../../service/operation-service";

@Component({
  selector: 'app-expression-calculator',
  templateUrl: './expression-calculator.component.html',
  styleUrls: ['./expression-calculator.component.scss']
})
export class ExpressionCalculatorComponent implements OnInit {


  result: string;
  expression: string;
  expressionForm: FormControl;

  onSubmit(): void {

    this.expression = this.expressionForm.value;

    this.operationService.calculateExpression(this.expression).subscribe(
      data => {
        this.result = data;
      },
      (error: HttpErrorResponse) => {
        this.result = error.error
        console.log(error.message);
        console.log(error);
      });
  }

  constructor(private operationService: OperationService) {
  }

  ngOnInit(): void {
    this.expressionForm = new FormControl(null, [
      Validators.required,
      Validators.pattern('^[1-9]+[0-9]*$')
    ])
    this.expression = "";

  }

  getErrorMessage(): string {
    if (this.expressionForm.hasError('required')) {
      return 'Where is the expression??\n'
    }
  }

}
