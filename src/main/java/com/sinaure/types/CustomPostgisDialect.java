package com.sinaure.types;

import java.sql.Types;

public class CustomPostgisDialect extends org.hibernate.spatial.dialect.postgis.PostgisDialect {

	public CustomPostgisDialect() {
		super();
		System.out.println("Register Hibernate Type ... ");
		this.registerHibernateType(Types.ARRAY, StringArrayType.class.getName());

        System.out.println("Register Column Type ... ");
        this.registerColumnType(Types.ARRAY,  "text[]");
		
	}
}
