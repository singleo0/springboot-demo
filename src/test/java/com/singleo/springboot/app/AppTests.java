package com.singleo.springboot.app;

import com.singleo.springboot.app.service.CompanyService;
import com.singleo.springboot.common.handler.LogConfigHandler;
import com.singleo.springboot.common.swaparea.SwapAreaUtils;
import com.singleo.springboot.common.utils.FrameworkUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTests {

    @Test
    public void testA() throws Exception {
        SwapAreaUtils.buildNewSwapArea();
        SwapAreaUtils.setEvtTraceId("test-123");
        ((LogConfigHandler)FrameworkUtil.getBean("logConfigHandler")).addLogConfigKeys(Arrays.asList("evtTraceId"));
        CompanyService companyService =(CompanyService) FrameworkUtil.getBean("companyService");
        companyService.multiThreadTrace("123");
        System.out.println("123");
    }
}
