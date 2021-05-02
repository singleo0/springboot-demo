package com.singleo.springboot.app.controller;

import com.singleo.springboot.app.dao.CompanyDao;
import com.singleo.springboot.app.service.CompanyService;
import com.singleo.springboot.common.utils.Result;
import com.singleo.springboot.common.utils.StateCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/company")
public class CompanyController {
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource
    CompanyDao companyDao;

    @Resource
    CompanyService companyService;

    @RequestMapping(value = "/findAll",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Result findAllTrace() throws Exception {
        logger.info("findall===");
        companyService.multiThreadTrace("test args");
//        throw new Exception("test ex");
        return Result.resultInfo(StateCode.SUCCESS, companyDao.findAll()) ;
    }

    @Transactional
    @GetMapping("/selectAll")
    public Result selectAll(){
        logger.info("selectAll===");
        return Result.resultInfo(StateCode.SUCCESS, companyDao.selectAll()) ;
    }

    @GetMapping("/selectById")
    public Result selectById(@RequestParam("id") long id){
        logger.info("selectById===");
        return Result.resultInfo(StateCode.SUCCESS, companyDao.selectById(id)) ;

    }

    //只有company表是innodb,事务才能生效
//    @GetMapping("/testTransactional")
//    @MultiDataSourceTransactional(transactionManagers = {"transactionManager","appTransactionManager"})
//    public int testTransactional(){
//
//        List list=companyDao.selectAll();
//        companyDao.insertCompany();
//        list=companyDao.selectAll();
//        Result result1=workflowController.findTaskList("huiqian2","70018", "1");
//        result1=workflowController.findTaskList("huiqian2","70018", "3");
//        workflowController.submitTask("70018", "huiqian2");
//        result1=workflowController.findTaskList("huiqian2","70018", "1");
//        result1=workflowController.findTaskList("huiqian2","70018", "3");
//        String str=null;
//        str.equals("");
//        return 1;
//    }

    @GetMapping("/testHello")
    public String testHello(){
        return "this is a test hello";
    }
}
