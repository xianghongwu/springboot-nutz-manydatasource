package com.xhw.nutzmanydatasource.configure.nutz;


import com.xhw.nutzmanydatasource.configure.properties.NutzDaoRuntimeProperties;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.lang.Each;
import org.nutz.lang.Lang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ConditionalOnBean({ Dao.class })
@EnableConfigurationProperties(NutzDaoRuntimeProperties.class)
@AutoConfigureAfter({ NutzDaoAutoConfiguration.class })
public class NutzDataBaseIniter {
    @Autowired
    private NutzDaoRuntimeProperties nutzDaoRuntimeProperties;

    /*因为这里引入的是第一个数据源，说以自动建表就只会在第一个数据库中*/
    @Autowired
    private Dao dao;

    @Autowired
    private Dao secondaryDao;

    @PostConstruct
    public void create() {
        if (nutzDaoRuntimeProperties.isCreate()) {
            Lang.each(nutzDaoRuntimeProperties.getBasepackage(), (Each<String>) (arg0, pkg, arg2) -> {
                /*因为这里引入的是第一个数据源，说以自动建表就只会在第一个数据库中  (dao)*/
                Daos.createTablesInPackage(dao, pkg, nutzDaoRuntimeProperties.isFoceCreate());
            });
        }
        if (nutzDaoRuntimeProperties.isMigration()) {
            Lang.each(nutzDaoRuntimeProperties.getBasepackage(), (Each<String>) (arg0, pkg, arg2) -> {
                /*因为这里引入的是第一个数据源，说以自动建表就只会在第一个数据库中  (dao)*/
                Daos.migration(dao, pkg, nutzDaoRuntimeProperties.isAddColumn(), nutzDaoRuntimeProperties.isDeleteColumn(), nutzDaoRuntimeProperties.isCheckIndex());
            });
        }
    }
}
