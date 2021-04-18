import {Component, Input, OnInit} from '@angular/core';
import {OperationInput} from '../../models/OperationInput'
import {FormGroup, FormControl, Validators} from '@angular/forms';
import {OperationService} from "../../service/operation-service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-adunare',
  templateUrl: './operation.component.html',
  styleUrls: ['./operation.component.scss']
})
export class OperationComponent implements OnInit {
  result: string;
  error: string;
  operationInput: OperationInput;
  numberForm: FormGroup;

  @Input()
  operation: string;

  onSubmit(): void {

    this.operationInput.firstNumber = this.numberFormControl.firstNumber.value;
    this.operationInput.secondNumber = this.numberFormControl.secondNumber.value;

    this.operationService.calculate(this.operation, this.operationInput).subscribe(
      data => {
        this.result = data;
        this.error = null;
      },
      (error: HttpErrorResponse) => {
        this.error = error.error
        this.result = null;
      });
  }

  constructor(private operationService: OperationService) {
  }

  ngOnInit(): void {
    this.numberForm = new FormGroup({
      firstNumber: new FormControl(null, [
        Validators.required,
        Validators.pattern('^[1-9]+[0-9]*$')
      ]),
      secondNumber: new FormControl(null, [
        Validators.required,
        Validators.pattern('^0|([1-9]+[0-9]*)$')
      ])
    });
    this.operationInput = {firstNumber: "", secondNumber: ""}

  }

  getErrorMessage(): string {
    if (this.numberFormControl.firstNumber.hasError('pattern') || this.numberFormControl.secondNumber.hasError('pattern')) {
      return 'Is that even a number?\n'
    } else if (this.numberFormControl.firstNumber.hasError('required') || this.numberFormControl.secondNumber.hasError('required')) {
      return 'Where are the numbers???\n'
    }
  }


  get numberFormControl() {
    return this.numberForm.controls;
  }

  onTyping(typed: string):void{
    this.error = null;
    console.log(typed);
  }

}
