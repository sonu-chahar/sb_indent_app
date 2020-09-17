package com.chahar.indent.config;

import org.hibernate.dialect.MySQL5InnoDBDialect;

public class CustomMysql5WithBitBooleanDialect extends MySQL5InnoDBDialect {
	public CustomMysql5WithBitBooleanDialect() {
        super();
        registerColumnType( java.sql.Types.BOOLEAN, "bit" );        
    }
}