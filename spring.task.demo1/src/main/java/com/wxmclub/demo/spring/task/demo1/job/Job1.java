/*
 * Copyright [2016] [WangXiaoMing]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package com.wxmclub.demo.spring.task.demo1.job;

import com.wxmclub.demo.spring.task.demo1.util.LogUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 任务作业,需要继承指定基类
 * <p>
 * Created by Ming on 16/9/16.
 */
public class Job1 extends QuartzJobBean {

    /** 自定义属性 */
    private int timeout;

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * 需要调度的具体任务
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LogUtil.info("定时任务1执行中…");
    }

}
