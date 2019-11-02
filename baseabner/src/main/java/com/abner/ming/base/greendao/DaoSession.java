package com.abner.ming.base.greendao;

import com.abner.ming.base.model.CacheBean;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.Map;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig cacheBeanDaoConfig;

    private final CacheBeanDao cacheBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        cacheBeanDaoConfig = daoConfigMap.get(CacheBeanDao.class).clone();
        cacheBeanDaoConfig.initIdentityScope(type);

        cacheBeanDao = new CacheBeanDao(cacheBeanDaoConfig, this);

        registerDao(CacheBean.class, cacheBeanDao);
    }
    
    public void clear() {
        cacheBeanDaoConfig.clearIdentityScope();
    }

    public CacheBeanDao getCacheBeanDao() {
        return cacheBeanDao;
    }

}
