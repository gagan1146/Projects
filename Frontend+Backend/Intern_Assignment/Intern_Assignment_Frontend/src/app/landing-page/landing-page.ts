import { Component } from '@angular/core';
import { DeviceSummaryPage } from '../device-summary-page/device-summary-page';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-landing-page',
  imports: [DeviceSummaryPage,RouterLink],
  templateUrl: './landing-page.html',
  styleUrl: './landing-page.css',
  standalone: true,
})
export class LandingPage {

}
