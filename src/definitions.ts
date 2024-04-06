export interface CapNotifyPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
