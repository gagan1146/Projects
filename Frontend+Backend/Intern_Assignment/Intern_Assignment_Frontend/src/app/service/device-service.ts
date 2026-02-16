import { Injectable } from '@angular/core';
import { DeviceModel } from '../models/DeviceModel';

export interface Device {
  name: string;
  shelf: string;
}

@Injectable({
  providedIn: 'root',
})
export class DeviceService {
  devices: DeviceModel[] = [
    {
      id: 'D001',
      deviceName: 'Core Router',
      partNumber: 1001,
      BuildingName: 'Main HQ',
      DeviceType: 'Networking',
      numberOfShelf: 4,
    },
    {
      id: 'D002',
      deviceName: 'Edge Switch',
      partNumber: 2002,
      BuildingName: 'Data Center A',
      DeviceType: 'Switching',
      numberOfShelf: 2,
    },
    {
      id: 'D003',
      deviceName: 'Firewall Appliance',
      partNumber: 3003,
      BuildingName: 'Security Block',
      DeviceType: 'Security',
      numberOfShelf: 1,
    },
    {
      id: 'D004',
      deviceName: 'Storage Server',
      partNumber: 4004,
      BuildingName: 'Data Center B',
      DeviceType: 'Storage',
      numberOfShelf: 6,
    },
    {
      id: 'D005',
      deviceName: 'Wireless Controller',
      partNumber: 5005,
      BuildingName: 'Office Tower',
      DeviceType: 'Wireless',
      numberOfShelf: 3,
    },
  ];
}
