package com.peramdy.filter;

import com.alibaba.druid.filter.stat.StatFilter;

/**
 * Created by peramdy on 2017/12/16.
 */
public class MergeStatFilter extends StatFilter {


    /**
     * druid sql监控设置
     */

    public MergeStatFilter() {

        super.setDbType("");
        super.setLogSlowSql(true);
        super.setMergeSql(true);
        super.setSlowSqlMillis(10000);
        super.setConnectionStackTraceEnable(true);
    }
}
