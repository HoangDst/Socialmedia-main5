package com.example;

import com.example.config.DBException;

public interface IMysqlRepository<P, ID> {
    P insert(P p);

    P findById(ID id) throws DBException;
}
