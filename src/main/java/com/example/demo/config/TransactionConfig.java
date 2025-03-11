//package com.example.demo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.TransactionDefinition;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.transaction.support.DefaultTransactionDefinition;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableTransactionManagement
//public class TransactionConfig {
//
//    @Bean
//    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
//        DataSourceTransactionManager txManager = new DataSourceTransactionManager(dataSource);
//        // 设置事务属性
//        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//        def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
//        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//        txManager.setDefaultTimeout(30); // 30秒超时
//        return txManager;
//    }
//}