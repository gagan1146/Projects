import { Routes } from '@angular/router';
import { LandingPage } from './landing-page/landing-page';
import { DeviceCreationPage } from './device-creation-page/device-creation-page';
import { DeviceSummaryPage } from './device-summary-page/device-summary-page';
import { ShelfCreationPage } from './shelf-creation-page/shelf-creation-page';
import { ShelfSummaryPage } from './shelf-summary-page/shelf-summary-page';
import { PageNotFoundPage } from './page-not-found-page/page-not-found-page';
import { LoginComponent } from './login/login';
import { SignupComponent } from './signup/signup';
import { DeviceOnlyPage } from './device-only-page/device-only-page';
import { ShelfOnlyPage } from './shelf-only-page/shelf-only-page';
import { RoutingPage } from './routing-page/routing-page';
import { AddShelvesToDevice } from './add-shelves-to-device/add-shelves-to-device';
import { ShelfCreationByDevice } from './shelf-creation-by-device/shelf-creation-by-device';

export const routes: Routes = [
    {path:'',component:LandingPage},
    {path:'login',component:LoginComponent},
    {path:'signup',component:SignupComponent},
    {path:'device/creation',component:DeviceCreationPage},
    {path:'device/summary',component:DeviceSummaryPage},
    {path:'shelf/creation/:deviceId/:shelfPositionId',component:ShelfCreationPage},
    {path:'shelf/updation/:shelfPositionId',component:ShelfOnlyPage},
    {path:'shelf/summary',component:ShelfSummaryPage},
    {path:'shelf/creation/byDevice',component:ShelfCreationByDevice},
    {path:'route/summary',component:RoutingPage},
    {path:"device/summary/device/:deviceId",component:DeviceOnlyPage},
    {path:'**',component:PageNotFoundPage}

];
