import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { BrandService } from '../../service/brand-service';

@Component({
  selector: 'app-brand',
  imports: [ReactiveFormsModule], // 👈 បន្ថែម ReactiveFormsModule
  templateUrl: './brand.html',
  styleUrls: ['./brand.css'],
})
export class Brand implements OnInit {
  form!: FormGroup; // ✅ remove private so template can access

  constructor(private fb: FormBuilder, private brandService: BrandService) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      name: [''],
    });
  }

  createBrand() {
    this.brandService.saveBrand(this.form.value).subscribe((brand) => {
      console.log('Brand created:', brand);
    });
  }
}
