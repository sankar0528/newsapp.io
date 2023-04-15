/* IMPORTS */
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';


/* DEFINITION & EXPORT */
@Component({
    selector: 'app-register-form',
    templateUrl: './register-form.component.html',
    styleUrls: ['./register-form.component.scss']
})
export class RegisterFormComponent implements OnInit {

    // Declarations
    public formData: FormGroup;
    @Output() formSubmit = new EventEmitter();

    // Inject FormBuilder
    constructor(private FormBuilder: FormBuilder) { }

    // Method to reset form
    private resetForm = () => {
        this.formData = this.FormBuilder.group({
            username: [null, Validators.required],
            password: [null, Validators.required]
        });
    };

    // Start
    ngOnInit() {
        this.resetForm();
    }

}
