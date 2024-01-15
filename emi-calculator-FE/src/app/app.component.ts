import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, ReactiveFormsModule, HttpClientModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})



export class AppComponent {

  calculatorForm = this.formBuilder.group({
    email: ['', [Validators.required, Validators.email]],
    loanTermInYears: ['', [Validators.required, Validators.min(0), Validators.max(30), Validators.pattern("^[0-9]*$")]],
    yearlyInterestRate: ['', [Validators.required, Validators.min(0), Validators.max(100), Validators.pattern("^[0-9]*\\.?[0-9]+$")]],
    loanValue: ['', [Validators.required, Validators.pattern("^[0-9]*\\.?[0-9]+$")]]
  });

 submitMessage = false;

 response= {
  message:'',
  result: ''
 }


  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient
  ) {}

  title = 'emi-calculator-FE';

  onSubmit(): void {

    this.http.post('http://localhost:8080/calculate-emi', this.calculatorForm.value).subscribe((data: any) => {
    this.submitMessage=true;
    this.response.message = data.message;
    this.response.result = data.emi;
       console.warn('Your data has been submitted', data);
     },
     (error) => {
      this.submitMessage=true;
          this.response.message = error.error.message;
        }
    )

  }

  onTouched(): void {
    this.submitMessage=false;
  }

}

