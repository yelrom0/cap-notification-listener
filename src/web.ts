import { WebPlugin } from '@capacitor/core';

import type { CapNotifyPlugin } from './definitions';

export class CapNotifyWeb extends WebPlugin implements CapNotifyPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
