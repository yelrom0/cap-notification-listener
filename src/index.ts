import { registerPlugin } from '@capacitor/core';

import type { CapNotifyPlugin } from './definitions';

const CapNotify = registerPlugin<CapNotifyPlugin>('CapNotify', {
  web: () => import('./web').then(m => new m.CapNotifyWeb()),
});

export * from './definitions';
export { CapNotify };
