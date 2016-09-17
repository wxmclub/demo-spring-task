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
package com.wxmclub.demo.spring.task.demo2.job;

import com.wxmclub.demo.spring.task.demo2.util.LogUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 使用Spring-task中的注解实现定时任务
 *
 * Created by Ming on 16/9/16.
 */
@Component("job4")
public class Job4 {

    @Scheduled(cron = "2/5 * * * * ?")
    public void doTask() {
        LogUtil.info("定时任务4执行中…");
    }
}
