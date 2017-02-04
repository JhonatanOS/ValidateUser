package com.dev.joliveira.login;

/**
 * Created by ADM on 04/02/2017.
 */

public class SqlScript {

    //Script to create table

    public static String getCreateTable() {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("_id                          INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("USER                VARCHAR(200), ");
        sqlBuilder.append("PASSWORD            VARCHAR(200) ");
        sqlBuilder.append(");");
        return sqlBuilder.toString();
    }

}
