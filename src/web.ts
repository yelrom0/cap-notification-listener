import { WebPlugin } from '@capacitor/core';

import type { AppListReturn, CapNotifyPlugin } from './definitions';

export class CapNotifyWeb extends WebPlugin implements CapNotifyPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
  async listApps(showSystemApps: boolean): Promise<AppListReturn> {
    showSystemApps;
    console.log('This method is not implemented on web');
    return {appList: []};
  }

}
