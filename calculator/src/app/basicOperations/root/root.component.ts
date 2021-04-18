import {Component, Input, OnInit} from '@angular/core';
import {OperationInput} from "../../models/OperationInput";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpErrorResponse} from "@angular/common/http";
import {OperationService} from "../../service/operation-service";

@Component({
  selector: 'app-root',
  templateUrl: './root.component.html',
  styleUrls: ['./root.component.scss']
})
export class RootComponent implements OnInit {

  result: string;
  number: string;
  numberForm: FormControl;

  onSubmit(): void {

    this.number  = this.numberForm.value;

    this.operationService.root(this.number).subscribe(
      data =>{
        this.result = data;
      },
      (error: HttpErrorResponse) => {this.result = error.error
        console.log(error.message);
        console.log(error);
      });
  }

  constructor(private operationService: OperationService) {
  }

  ngOnInit(): void {
    this.numberForm = new FormControl(null,[
        Validators.required,
        Validators.pattern('^[1-9]+[0-9]*$')
      ])
    this.number = "";

  }

  getErrorMessage(): string {
    if (this.numberForm.hasError('required')) {
      return 'Where is the number?\n'
    }
    else if (this.numberForm.hasError('pattern')){
      return 'Is that even a number?'
    }
  }



}
