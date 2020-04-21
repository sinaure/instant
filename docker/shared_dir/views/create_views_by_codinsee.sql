create or replace function get_codcom() returns setof integer as $$
	select distinct code_com from mutations_parcel_by_codcom  order by code_com desc
$$ language sql stable;

select get_codcom();

DO
$$
	DECLARE
	    var_r   integer;
	    code   text;
	    boolean_var boolean;
	BEGIN
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
	        EXECUTE('INSERT INTO mutations_parcel_06'|| code ||' SELECT stats.*, (stats.totval/stats.transactions) as mean_val  from (SELECT anneemut, COUNT(*) as transactions, SUM(valeurfonc) as totval from mutations_parcel_view  where code_com='|| var_r ||' GROUP BY anneemut) as stats') ;
	    END LOOP;
	END
$$
LANGUAGE 'plpgsql';