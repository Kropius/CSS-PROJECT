import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, throwError} from 'rxjs';
import {catchError, retry} from 'rxjs/operators'
import {OperationInput} from "../models/OperationInput";
import {ComplexOperationResult} from "../models/ComplexOperationResult";

@Injectable({
  providedIn: 'root'
})
export class OperationService {
  url: string = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }

  public calculate(symbol: string, operationInput: OperationInput): Observable<string> {
    if (symbol === 'Add') {
      return this.add(operationInput);
    } else if (symbol == 'Minus') {
      return this.substraction(operationInput);
    } else if (symbol == 'Power') {
      return this.power(operationInput);
    } else if (symbol == 'Division') {
      return this.division(operationInput)
    } else if (symbol == 'Multiply') {
      return this.multiply(operationInput);
    }
  }

  public root(rootOperand: string): Observable<string> {
    return this.http.post(`${this.url}/operation/square`, rootOperand, {
      responseType: 'text'
    })
  }

  public calculateExpression(expression: string): Observable<ComplexOperationResult> {
    return this.http.post<ComplexOperationResult>(`${this.url}/compound/`, expression)
  }

  private add(operationInput: OperationInput): Observable<string> {
    return this.http.post(`${this.url}/operation/addition`, operationInput, {
      responseType: 'text'
    })
  }

  private multiply(operationInput: OperationInput): Observable<string> {
    return this.http.post(`${this.url}/operation/multiplication`, operationInput, {
      responseType: 'text'
    })
  }

  private substraction(operationInput: OperationInput): Observable<string> {
    return this.http.post(`${this.url}/operation/substraction`, operationInput, {
      responseType: 'text'
    })
  }

  private division(operationInput: OperationInput): Observable<string> {
    return this.http.post(`${this.url}/operation/division`, operationInput, {
      responseType: 'text'
    })
  }

  private power(operationInput: OperationInput): Observable<string> {
    return this.http.post(`${this.url}/operation/power`, operationInput, {
      responseType: 'text'
    })
  }


}
