package com.example;

import com.example.config.DBException;
import jakarta.annotation.PostConstruct;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.impl.TableImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

public abstract class BaseMysqlRepository<R extends Record, P, ID> implements IMysqlRepository<P, ID> {

    @Autowired
    protected DSLContext dslContext;

    private Class<P> pClass;

    @PostConstruct
    public void init() {
        this.pClass = ((Class<P>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1]);
    }

    public abstract TableImpl<R> getTable();

    @Override
    public P insert(P p) {
        return null;
    }

    @Override
    public P findById(ID id) throws DBException {
        Field<ID> idField = getIdField();
        if (idField == null) throw new DBException("id not found");
        return dslContext.select()
                .from(getTable())
                .where(idField.eq(id))
                .fetchOneInto(pClass);
    }

    protected Field<ID> getIdField() {
        return (Field<ID>) Arrays.stream(getTable().fields())
                .filter(field -> field.getName().equals("id"))
                .findFirst()
                .orElse(null);
    }
}
