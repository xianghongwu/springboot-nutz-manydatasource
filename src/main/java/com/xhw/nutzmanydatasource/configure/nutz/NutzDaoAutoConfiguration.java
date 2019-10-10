package com.xhw.nutzmanydatasource.configure.nutz;

import org.nutz.dao.Dao;
import org.nutz.dao.SqlManager;
import org.nutz.dao.impl.DaoRunner;
import org.nutz.dao.impl.NutDao;
import org.nutz.integration.spring.SpringDaoRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ConditionalOnClass({Dao.class})
@AutoConfigureAfter({SqlManagerAutoConfiguration.class})
public class NutzDaoAutoConfiguration {
    @Autowired
    DaoRunner daoRunner;

    @Autowired
    DataSource primaryDataSource;

    @Autowired
    DataSource secondaryDataSource;
    @Autowired
    SqlManager sqlManager;

    @Bean("dao")
    public Dao primaryDao() {
        NutDao dao = new NutDao(primaryDataSource, sqlManager);
        dao.setRunner(daoRunner);
        return dao;
    }

    @Bean("secondaryDao")
    public Dao secondaryDao() {
        NutDao dao = new NutDao(secondaryDataSource, sqlManager);
        dao.setRunner(daoRunner);
        return dao;
    }

    @Bean
    @ConditionalOnMissingBean(DaoRunner.class)
    public DaoRunner daoRunner() {
        return new SpringDaoRunner();
    }
}
