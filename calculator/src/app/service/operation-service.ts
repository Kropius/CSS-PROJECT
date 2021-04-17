import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, throwError} from 'rxjs';
import {catchError, retry} from 'rxjs/operators'
import {OperationInput} from "../models/OperationInput";

@Injectable({
  providedIn: 'root'
})
export class OperationService {
  url: string = 'http://localhost:8080/operation';

  constructor(private http: HttpClient) {
  }

  calculate(symbol: string, operationInput: OperationInput): Observable<string>{
    if (symbol === 'Add') {
      return this.add(operationInput);
    } else if (symbol == 'Minus') {
      return this.substraction(operationInput);
    } else if (symbol == 'Power') {
      return this.power(operationInput);
    } else if (symbol == 'Division') {
      return this.division(operationInput)
    } else if (symbol == 'Multiply') {
      return  this.multiply(operationInput);
    } else if (symbol == 'Square root') {
      return this.square(operationInput);
    }
  }

  private add(operationInput: OperationInput): Observable<string> {
    return this.http.post(`${this.url}/addition`, operationInput, {
      responseType: 'text'
    })
  }

  private multiply(operationInput: OperationInput): Observable<string> {
    return this.http.post(`${this.url}/multiplication`, operationInput, {
      responseType: 'text'
    })
  }

  private substraction(operationInput: OperationInput): Observable<string> {
    return this.http.post(`${this.url}/substraction`, operationInput, {
      responseType: 'text'
    })
  }

  private division(operationInput: OperationInput): Observable<string> {
    return this.http.post(`${this.url}/division`, operationInput, {
      responseType: 'text'
    })
  }

  private power(operationInput: OperationInput): Observable<string> {
    return this.http.post(`${this.url}/power`, operationInput, {
      responseType: 'text'
    })
  }

  private square(operationInput: OperationInput): Observable<string> {
    return this.http.post(`${this.url}/square`, operationInput, {
      responseType: 'text'
    })
  }
}
