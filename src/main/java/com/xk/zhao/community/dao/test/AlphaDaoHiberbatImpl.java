package com.xk.zhao.community.dao.test;

import org.springframework.stereotype.Repository;

@Repository
public class AlphaDaoHiberbatImpl implements AlphaDao {

    @Override
    public String select() {
        return "Hiberbat";
    }
}
