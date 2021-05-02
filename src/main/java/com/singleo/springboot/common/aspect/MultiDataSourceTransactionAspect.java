package com.singleo.springboot.common.aspect;


import javafx.util.Pair;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Stack;

/**
 * 多数据源事务切面
 * ※采用Around似乎不行※
 */
@Component
@Aspect
public class MultiDataSourceTransactionAspect implements ApplicationContextAware {

    /**
     * 线程本地变量：为什么使用栈？※为了达到后进先出的效果※
     */
    private static final ThreadLocal<Stack<Pair<DataSourceTransactionManager, TransactionStatus>>> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 用于获取事务管理器
     */
    private ApplicationContext applicationContext;

    /**
     * 事务声明
     */
    private DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    {
        // 非只读模式
        def.setReadOnly(false);
        // 事务隔离级别：采用数据库的
        def.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
        // 事务传播行为
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    }

    /**
     * 切面
     */
    @Pointcut("@annotation(com.singleo.springboot.common.aspect.MultiDataSourceTransactional)")
    public void pointcut() {
    }

    /**
     * 声明事务
     *
     * @param transactional 注解
     */
    @Before("pointcut() && @annotation(transactional)")
    public void before(MultiDataSourceTransactional transactional) {
        // 根据设置的事务名称按顺序声明，并放到ThreadLocal里
        String[] transactionManagerNames = transactional.transactionManagers();
        Stack<Pair<DataSourceTransactionManager, TransactionStatus>> pairStack = new Stack<>();
        for (String transactionManagerName : transactionManagerNames) {
            DataSourceTransactionManager transactionManager = applicationContext.getBean(transactionManagerName, DataSourceTransactionManager.class);
            TransactionStatus transactionStatus = transactionManager.getTransaction(def);
            pairStack.push(new Pair(transactionManager, transactionStatus));
        }
        THREAD_LOCAL.set(pairStack);
    }

    /**
     * 提交事务
     */
    @AfterReturning("pointcut()")
    public void afterReturning() {
        // ※栈顶弹出（后进先出）
        Stack<Pair<DataSourceTransactionManager, TransactionStatus>> pairStack = THREAD_LOCAL.get();
        while (!pairStack.empty()) {
            Pair<DataSourceTransactionManager, TransactionStatus> pair = pairStack.pop();
            pair.getKey().commit(pair.getValue());
        }
        THREAD_LOCAL.remove();
    }

    /**
     * 回滚事务
     */
    @AfterThrowing(value = "pointcut()")
    public void afterThrowing() {
        // ※栈顶弹出（后进先出）
        Stack<Pair<DataSourceTransactionManager, TransactionStatus>> pairStack = THREAD_LOCAL.get();
        while (!pairStack.empty()) {
            Pair<DataSourceTransactionManager, TransactionStatus> pair = pairStack.pop();
            pair.getKey().rollback(pair.getValue());
        }
        THREAD_LOCAL.remove();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}