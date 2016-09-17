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
package com.wxmclub.demo.spring.task.demo3.controller;

import com.wxmclub.demo.spring.task.demo3.model.ScheduleJob;
import com.wxmclub.demo.spring.task.demo3.service.SchedulerUtil;
import com.wxmclub.demo.spring.task.demo3.util.LogUtil;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 *
 * Created by Ming on 16/9/16.
 */
@Controller
public class TaskController {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @PostConstruct
    public void init() throws SchedulerException {
        ScheduleJob job;
        for (int i = 0; i < 5; i++) {
            job = new ScheduleJob() {
                @Override
                public void doTask() {
                    LogUtil.info("执行初始示例任务[%s],desc=%s", this.getJobId(), this.getDesc());
                }
            };
            job.setJobId(String.format("jobId_%s", i));
            job.setJobGroup(String.format("jobGroup_%s", i));
            job.setJobName(String.format("jobName_%s", i));
            job.setCronExpression("0/5 * * * * ?");
            job.setDesc("初始示例任务");

            SchedulerUtil.saveScheduleJob(schedulerFactoryBean.getScheduler(), job);
        }
    }

    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response) throws SchedulerException {
        List<ScheduleJob> allJobs = SchedulerUtil.listAllJobs(schedulerFactoryBean.getScheduler());
        List<ScheduleJob> currentlyExecutingJobs = SchedulerUtil.getCurrentlyExecutingJobs(schedulerFactoryBean.getScheduler());

        request.setAttribute("test", "城市付电费收费");

        request.setAttribute("allJobs", allJobs);
        request.setAttribute("currentlyExecutingJobs", currentlyExecutingJobs);

        return "list.jsp";
        //ModelAndView view = new ModelAndView();
        //view.setViewName("list.jsp");
        //return view;
    }

    @RequestMapping("/addJob")
    public void addJob(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam String jobId,
                       @RequestParam String jobGroup,
                       @RequestParam String jobName,
                       @RequestParam String cronExpression,
                       @RequestParam String desc) throws SchedulerException, IOException {
        ScheduleJob job = new ScheduleJob() {
            public void doTask() {
                LogUtil.info("执行自定义添加任务[%s],desc=%s", this.getJobId(), this.getDesc());
            }
        };
        job.setJobId(jobId);
        job.setJobGroup(jobGroup);
        job.setJobName(jobName);
        job.setCronExpression(cronExpression);
        job.setDesc(desc);

        SchedulerUtil.saveScheduleJob(schedulerFactoryBean.getScheduler(), job);
        response.sendRedirect("/list");
    }

    /**
     * 暂停任务
     *
     * @param request
     * @param response
     * @param jobGroup
     * @param jobName
     * @throws SchedulerException
     * @throws IOException
     */
    @RequestMapping("/pauseJob")
    public void pauseJob(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam String jobGroup,
                         @RequestParam String jobName) throws SchedulerException, IOException {
        ScheduleJob job = new ScheduleJob();
        job.setJobGroup(jobGroup);
        job.setJobName(jobName);

        SchedulerUtil.pauseJob(schedulerFactoryBean.getScheduler(), job);
        response.sendRedirect("/list");
    }

    /**
     * 恢复任务
     *
     * @param request
     * @param response
     * @param jobGroup
     * @param jobName
     * @throws SchedulerException
     * @throws IOException
     */
    @RequestMapping("/resumeJob")
    public void resumeJob(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam String jobGroup,
                         @RequestParam String jobName) throws SchedulerException, IOException {
        ScheduleJob job = new ScheduleJob();
        job.setJobGroup(jobGroup);
        job.setJobName(jobName);

        SchedulerUtil.resumeJob(schedulerFactoryBean.getScheduler(), job);
        response.sendRedirect("/list");
    }

    /**
     * 删除任务
     *
     * @param request
     * @param response
     * @param jobGroup
     * @param jobName
     * @throws SchedulerException
     * @throws IOException
     */
    @RequestMapping("/deleteJob")
    public void deleteJob(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam String jobGroup,
                         @RequestParam String jobName) throws SchedulerException, IOException {
        ScheduleJob job = new ScheduleJob();
        job.setJobGroup(jobGroup);
        job.setJobName(jobName);

        SchedulerUtil.deleteJob(schedulerFactoryBean.getScheduler(), job);
        response.sendRedirect("/list");
    }

    /**
     * 立刻执行任务
     *
     * @param request
     * @param response
     * @param jobGroup
     * @param jobName
     * @throws SchedulerException
     * @throws IOException
     */
    @RequestMapping("/triggerJob")
    public void triggerJob(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam String jobGroup,
                         @RequestParam String jobName) throws SchedulerException, IOException {
        ScheduleJob job = new ScheduleJob();
        job.setJobGroup(jobGroup);
        job.setJobName(jobName);

        SchedulerUtil.triggerJob(schedulerFactoryBean.getScheduler(), job);
        response.sendRedirect("/list");
    }

}
