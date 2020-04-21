CREATE TABLE IF NOT EXISTS mutations_by_year ( aneemut integer,  transactions bigint,  totval float, mean_val float, CONSTRAINT mutations_by_year_pk PRIMARY KEY (aneemut));

CREATE MATERIALIZED VIEW IF NOT EXISTS mutations_loc_vw AS SELECT
t1.*,
substr(t1.l_codinsee[1],3,3)::integer as code_com,
ST_AsGeoJSON(ST_Transform(t2.geom,4326)) as point
FROM   mutations  t1
LEFT JOIN   mutations_geomlocmut t2 ON t1.idmutation = t2.idmutation::bigint
WITH NO DATA;

REFRESH MATERIALIZED VIEW mutations_loc_vw;

CREATE MATERIALIZED VIEW IF NOT EXISTS  mutations_parcel_vw AS SELECT
t1.*,
substr(t1.l_codinsee[1],3,3)::integer as code_com,
ST_AsGeoJSON(ST_Transform(t3.geom,4326)) as geometry_parcel, ST_AsGeoJSON(ST_Transform(t4.geom,4326)) as geometry_parcel_mutation
FROM   mutations  t1
LEFT JOIN   mutations_geompar t3 ON t1.idmutation = t3.idmutation::bigint
LEFT JOIN   mutations_geomparmut t4 ON t1.idmutation = t4.idmutation::bigint
WITH NO DATA;

REFRESH MATERIALIZED VIEW mutations_parcel_vw;


CREATE MATERIALIZED VIEW mutations_parcel_by_cod as (SELECT nested.*, (nested.val/nested.transactions) as mean_val from (SELECT code_com, COUNT(*) as transactions, SUM(valeurfonc) as val from mutations_parcel_vw where coddep='06' GROUP BY code_com ) as nested  where nested.val>0 and transactions>1 ORDER BY mean_val DESC);
CREATE VIEW mutations_parcel_by_codcom  AS SELECT mv.* from mutations_parcel_by_cod mv;

CREATE MATERIALIZED VIEW mutations_loc_by_cod as (SELECT nested.*, (nested.val/nested.transactions) as mean_val from (SELECT code_com, COUNT(*) as transactions, SUM(valeurfonc) as val from mutations_parcel_vw where coddep='06' GROUP BY code_com ) as nested  where nested.val>0 and transactions>1 ORDER BY mean_val DESC);
CREATE VIEW mutations_loc_by_codcom  AS SELECT mv.* from mutations_loc_by_cod mv;
