# cap-notification-listener

Allows for the listing of apps and the selection of apps to listen to notifications

## Install

```bash
npm install cap-notification-listener
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`listApps(...)`](#listapps)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### listApps(...)

```typescript
listApps(showSystemApps: boolean) => Promise<AppListReturn>
```

| Param                | Type                 |
| -------------------- | -------------------- |
| **`showSystemApps`** | <code>boolean</code> |

**Returns:** <code>Promise&lt;<a href="#applistreturn">AppListReturn</a>&gt;</code>

--------------------


### Interfaces


#### AppListReturn

| Prop          | Type                |
| ------------- | ------------------- |
| **`appList`** | <code>string</code> |

</docgen-api>
