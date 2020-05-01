CREATE TABLE IF NOT EXISTS mutations_by_year ( anneemut integer,  transactions bigint,  totval float, mean_val float, PRIMARY KEY (anneemut));
CREATE TABLE IF NOT EXISTS mutations_parcel_06 ( anneemut integer,  transactions bigint,  totval float, mean_val float, code_com integer, code_dep integer , PRIMARY KEY (code_com , anneemut));
DELETE FROM mutations_parcel_06;

create or replace function get_codcom() returns setof integer as $$
	select distinct code_com from mutations_parcel_by_codcom  order by code_com desc
$$ language sql stable;

DO
$$
	DECLARE
	    var_r   integer;
	    code   text;
	    code_dep   text;
	    code_com   text;
	    boolean_var boolean;
	BEGIN
	    code_dep:=  LPAD('06'::text, 2, '0');
		FOR var_r IN select get_codcom()
	    LOOP
	    	code:= LPAD(var_r::text, 3, '0');
	        SELECT EXISTS ( SELECT FROM pg_catalog.pg_class c  JOIN   pg_catalog.pg_namespace n ON n.oid = c.relnamespace  WHERE  n.nspname = 'public'  AND    c.relname = 'mutations_parcel_06' || code || ''  ) INTO boolean_var;
			IF boolean_var THEN 
			    RAISE NOTICE ' DELETING OLD TABLE  mutations_parcel_06%', code;
	        	EXECUTE('DROP TABLE mutations_parcel_06'|| code ||'');
	        END IF;
	        RAISE NOTICE ' CREATING TABLE  mutations_parcel_06%', code;
	        EXECUTE('CREATE TABLE mutations_parcel_06'|| code ||' () INHERITS (mutations_by_year)');
	        RAISE NOTICE ' INSERT IN TABLE  mutations_parcel_06%', code;
	        EXECUTE('INSERT INTO mutations_parcel_06'|| code ||' SELECT stats.*, (stats.totval/stats.transactions) as mean_val  from (SELECT anneemut, COUNT(*) as transactions, SUM(valeurfonc) as totval from mutations_parcel_vw  where code_com='|| var_r ||' GROUP BY anneemut) as stats') ;
	        RAISE NOTICE 'EXECUTING : INSERT INTO mutations_parcel_06 (anneemut , transactions, totval, mean_val, code_com, code_dep) SELECT stats.anneemut, stats.transactions, stats.totval, stats.mean_val, %, % as code_dep  FROM mutations_parcel_06% stats', code_com, code_dep, code ;

	        EXECUTE('INSERT INTO mutations_parcel_06 (anneemut , transactions, totval, mean_val, code_com, code_dep) SELECT stats.anneemut, stats.transactions, stats.totval, stats.mean_val, '||var_r||' ,  '||code_dep||'  FROM mutations_parcel_06'|| code ||' stats') ;
	    END LOOP;
	END
$$

ALTER TABLE mutations_parcel_06 OWNER TO instant;

LANGUAGE 'plpgsql';