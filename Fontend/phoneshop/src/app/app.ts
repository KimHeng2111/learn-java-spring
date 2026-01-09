import { Component, signal } from '@angular/core';
import { Brand } from './component/brand/brand';

@Component({
  selector: 'app-root',
  imports: [Brand],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('phoneshop');
}
