package com.example.restapimvc.querydsl;

import org.hibernate.dialect.PostgreSQL91Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class CustomPGDialect extends PostgreSQL91Dialect {
    public CustomPGDialect() {
        super();
        registerFunction("string_agg",
                new SQLFunctionTemplate(StandardBasicTypes.STRING,"string_agg(?1,?2)"));
    }
}
