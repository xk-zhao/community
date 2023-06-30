package com.xk.zhao.community.dao.test;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class AlphaDaoMyBatisImpl implements AlphaDao{
    @Override
    public String select() {
        return "mybatis";
    }
}
