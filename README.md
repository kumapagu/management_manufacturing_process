# management_manufacturing_process

## 要件
### 管理機能
1. 全ての案件の状態を管理することができる
   1. 案件の一覧を見ることができる
   2. 足場がかかっている状態かを確認することができる
   3. 撤収まで済んでいるかを確認することができる
   4. 請求済みかどうかを確認することができる
   5. 請求月の設定と確認をすることができる
2. 作業員の管理ができる
   1. 作業員の一覧を見ることができる
   2. 在職中かどうかを確認することができる
   3. 出勤状態の確認ができるかを確認することができる
   4. 当日稼働できる作業員を把握できるか
3. 当日の作業を管理することができる
   1. 当日に作業をする案件を把握することができる
   2. 案件ごとに従業員を割り当てることができる
   3. 当日に作業する案件を登録することができる
   4. 作成した工程表をメール等で送ることができる

### ユーザビリティ
1. PCとスマホの両方から操作することができる
   1. レスポンシブ対応
2. メールもしくはラインなどで工程表などを送ることができる
3. ログイン機能がある


## テーブル定義
### caseテーブル（案件テーブル）
| 物理名 | 論理名 | 型 | 制約 |
| ----- | ----- | -- | ---- |
| caseId | 案件ID | bigint | primary-key auto-increment |
| caseName | 案件名称 | varchar(50) | not-null  |
| clientId | 顧客ID | bigint | foreign-key |
| caseConditionId | 案件状態ID | bigint | foreign-key |
| billingConditionId | 請求状態ID | bigint | foreign-key |
| createdAt | 作成日 | datetime | not-null default-current-timestamp |
| updatedAt | 更新日 | datetime | not-null default-current_timestamp-on-update-current_timestamp |

### workerテーブル（作業員テーブル）
| 物理名 | 論理名 | 型 | 制約 |
| ----- | ----- | -- | ---- |
| workerId | 作業員ID | bigint | primary-key auto-increment |
| workerName | 作業員名 | varchar(50) | not-null  |
| workerNameKana | 作業員名(カナ) | varchar(50) | not-null |
| isRetirement | 退職フラグ | bit | not-null |
| vacationInformationId | 休暇情報ID | bigint | foreign-key |
| createdAt | 作成日 | datetime | not-null default-current-timestamp |
| updatedAt | 更新日 | datetime | not-null default-current_timestamp-on-update-current_timestamp |

### scheduleテーブル（工程表テーブル）
| 物理名 | 論理名 | 型 | 制約 |
| ----- | ----- | -- | ---- |
| scheduleId | 工程表ID | bigint | primary-key auto-increment |
| workingDay | 作業日 | datetime | not-null  |
| operationClass | 作業区分 | enum | not-null |
| caseId | 案件ID | bigint | foreign-key |
| workerIds | 作業員ID | bigint | foreign-key |
| createdAt | 作成日 | datetime | not-null default-current-timestamp |
| updatedAt | 更新日 | datetime | not-null default-current_timestamp-on-update-current_timestamp |

### billingConditionテーブル（請求状態テーブル）
| 物理名 | 論理名 | 型 | 制約 |
| ----- | ----- | -- | ---- |
| billingConditionId | 請求状態ID | bigint | primary-key auto-increment |
| billingYear | 請求年 | integer | not-null  |
| billingMonth | 請求月 | integer | not-null |
| isBilling | 請求状態フラグ | bit | not-null |
| caseId | 案件ID | bigint | foreign-key |
| createdAt | 作成日 | datetime | not-null default-current-timestamp |
| updatedAt | 更新日 | datetime | not-null default-current_timestamp-on-update-current_timestamp |

### caseConditionテーブル（案件状態テーブル）
| 物理名 | 論理名 | 型 | 制約 |
| ----- | ----- | -- | ---- |
| caseConditionId | 案件状態ID | bigint | primary-key auto-increment |
| condition | 案件状態 | enum | not-null  |
| caseId | 案件ID | bigint | foreign-key |
| createdAt | 作成日 | datetime | not-null default-current-timestamp |
| updatedAt | 更新日 | datetime | not-null default-current_timestamp-on-update-current_timestamp |

### vacationInformationテーブル（休暇情報テーブル）
| 物理名 | 論理名 | 型 | 制約 |
| ----- | ----- | -- | ---- |
| vacationInformationId | 休暇情報ID | bigint | primary-key auto-increment |
| vacationDay | 休暇日 | date | not-null  |
| isPassed | 過去日フラグ | bit | not-null |
| workerId | 作業員ID | bigint | foreign-key |
| createdAt | 作成日 | datetime | not-null default-current-timestamp |
| updatedAt | 更新日 | datetime | not-null default-current_timestamp-on-update-current_timestamp |

### clientテーブル（取引先テーブル）
| 物理名 | 論理名 | 型 | 制約 |
| ----- | ----- | -- | ---- |
| clientId | 取引先ID | bigint | primary-key auto-increment |
| clientName | 取引先名 | varchar(50) | not-null  |
| createdAt | 作成日 | datetime | not-null default-current-timestamp |
| updatedAt | 更新日 | datetime | not-null default-current_timestamp-on-update-current_timestamp |


## 実装方針
- backendはkotlin(springBoot)
- frontendは検討中
  - backOffice用なので、デザインコンポーネントを使用して画面を作成していく
  - デプロイ先はgithubPagesを検討