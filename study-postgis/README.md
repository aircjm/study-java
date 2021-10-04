# Getting Started



## docker DB
```shell
docker run -d \
 --name limon-postgres \
 -e POSTGRES_PASSWORD=123456 \
 -e ALLOW_IP_RANGE=0.0.0.0/0 \
 -e PGDATA=/var/lib/postgresql/data/pgdata \
 -v ~/docker/postgres:/var/lib/postgresql/data \
 -p 5432:5432 \
 postgis/postgis:13-3.1-alpine
```

## sql
```sql
-- 创建测试位置库
CREATE TABLE "study_gps"
(
"time"     timestamptz(3)        NOT NULL,
"dev_id"   varchar(36)           NOT NULL,
"location" geography(Point, 4326) NOT NULL,
"gps_num"  int4,
"gps_type" varchar(10)           NOT NULL,
"azimuth"  float4,
"gnd_rate" float4
) WITHOUT OIDS;
```
