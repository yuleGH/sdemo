package com.yule.demo.service.impl;

import com.yule.demo.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yule on 2018/7/4 22:36.
 */
public class DemoServiceImpl implements DemoService {
    private Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Override
    public void test(int start) {
        System.out.println(start);
    }

}
