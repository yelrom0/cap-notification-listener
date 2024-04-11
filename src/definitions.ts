export interface CapNotifyPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  listApps(showSystemApps: boolean): Promise<AppListReturn>;
}

export interface AppListReturn {
  appList: ProcessedPackageInfo[]
}

export interface ProcessedPackageInfo {
  packageName: string;
  name: string;
  icon: string;
}