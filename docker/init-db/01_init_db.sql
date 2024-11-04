DO $$ BEGIN
    IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'mmp') THEN
        CREATE DATABASE mmp
            with ENCODING='UTF8'
            LC_COLLATE='ja_JP.utf8'
            LC_CTYPE='ja_JP.utf8'
            TEMPLATE=template0;
    END IF;
END $$;

-- updatedAtに現在時刻をセットするFunctionを作成
create or replace function set_updated_at()
returns trigger as $$
begin
    new.updated_at = current_timestamp;
    return new;
end;
$$ language plpgsql;

-- タイムゾーンを設定
set time zone 'Asia/Tokyo';

-- テーブル作成
create table if not exists client (
    id serial primary key,
    client_name varchar(50) not null,
    client_name_kana varchar(50) not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp
);

create trigger set_updated_at
before update on client
for each row
execute procedure set_updated_at();

comment on table client is 'クライアントテーブル';
comment on column client.id is 'クライアントID';
comment on column client.client_name is 'クライアント名';
comment on column client.client_name_kana is 'クライアント名(カナ)';
comment on column client.created_at is '作成日時';
comment on column client.updated_at is '更新日時';

create table if not exists billing_condition (
    id serial primary key,
    billing_year int,
    billing_month int,
    is_billing boolean not null default false,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp
    );

create trigger set_updated_at
    before update on billing_condition
    for each row
    execute procedure set_updated_at();

comment on table billing_condition is '請求状態テーブル';
comment on column billing_condition.id is '請求状態ID';
comment on column billing_condition.billing_year is '請求年';
comment on column billing_condition.billing_month is '請求月';
comment on column billing_condition.created_at is '作成日時';
comment on column billing_condition.updated_at is '更新日時';

-- 案件状態が未着手、進行中、完了のenumを作成
create type caseConditionType as enum ('notStarted', 'inProgress', 'completed');
create table if not exists case_condition (
    id serial primary key,
    condition caseConditionType not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp
);

create trigger set_updated_at
    before update on case_condition
    for each row
    execute procedure set_updated_at();

Comment on table case_condition is '案件状態テーブル';
Comment on column case_condition.id is '案件状態ID';
Comment on column case_condition.condition is '案件状態';
Comment on column case_condition.created_at is '作成日時';
Comment on column case_condition.updated_at is '更新日時';


create table if not exists "case" (
    id serial primary key,
    name varchar(50) not null,
    client_id int references "client"(id),
    case_condition_id int unique references "case_condition"(id),
    billing_condition_id int unique references "billing_condition"(id),
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp
    );

create trigger set_updated_at
    before update on "case"
    for each row
    execute procedure set_updated_at();

comment on table "case" is '案件テーブル';
comment on column "case".id is '案件ID';
comment on column "case".name is '案件名';
comment on column "case".client_id is 'クライアントID';
comment on column "case".case_condition_id is '案件状況ID';
comment on column "case".billing_condition_id is '請求状況ID';
comment on column "case".created_at is '作成日時';
comment on column "case".updated_at is '更新日時';

create table if not exists vacation_information (
    id serial primary key,
    vacation_day int not null,
    is_passed boolean not null default false,
    worker_id int references "worker"(id),
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp
);

create trigger set_updated_at
    before update on vacation_information
    for each row
    execute procedure set_updated_at();

comment on table vacation_information is '休暇情報テーブル';
comment on column vacation_information.id is '休暇情報ID';
comment on column vacation_information.vacation_day is '休暇日';
comment on column vacation_information.is_passed is '過去フラグ';
comment on column vacation_information.created_at is '作成日時';
comment on column vacation_information.updated_at is '更新日時';

create table if not exists worker (
    id serial primary key,
    worker_name varchar(50) not null,
    worker_name_kana varchar(50) not null,
    is_retirement boolean not null default false,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp
    );

create trigger set_updated_at
    before update on worker
    for each row
    execute procedure set_updated_at();

comment on table worker is '作業員テーブル';
comment on column worker.worker_name is '作業員名';
comment on column worker.worker_name_kana is '作業員名(カナ)';
comment on column worker.is_retirement is '退職フラグ';
comment on column worker.vacation_information_id is '休暇情報ID';
comment on column worker.created_at is '作成日時';
comment on column worker.updated_at is '更新日時';

-- 足場を組み立てか撤去のenumを作成
create type operationType as enum ('assemble', 'dismantle');

create table if not exists schedule (
    id serial primary key,
    working_day date,
    operation_class operationType,
    case_id int references "case"(id),
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp
    );

create trigger set_updated_at
    before update on schedule
    for each row
    execute procedure set_updated_at();

comment on table schedule is '工程表テーブル';
comment on column schedule.id is '工程表ID';
comment on column schedule.working_day is '作業日';
comment on column schedule.operation_class is '作業種別';
comment on column schedule.case_id is '案件ID';
comment on column schedule.created_at is '作成日時';
comment on column schedule.updated_at is '更新日時';

create table if not exists cases_workers (
    worker_id int references worker(id),
    case_id int references "case"(id),
    primary key (worker_id, case_id)
    );

comment on table cases_workers is 'アサインテーブル';
comment on column cases_workers.worker_id is '作業員ID';
comment on column cases_workers.case_id is '案件ID';

alter table "case" add check (id >= 1);
alter table worker add check (id >= 1);
alter table schedule add check (id >= 1);
alter table cases_workers add check (worker_id >= 1);
alter table cases_workers add check (case_id >= 1);
alter table cases_schedules add check (schedule_id >= 1);
alter table cases_schedules add check (case_id >= 1);
alter table billing_condition add check (id >= 1);
alter table case_condition add check (id >= 1);
alter table client add check (id >= 1);
alter table vacation_information add check (id >= 1);


