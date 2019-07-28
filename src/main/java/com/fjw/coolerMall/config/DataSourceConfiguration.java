package com.fjw.coolerMall.config;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.fjw.coolerMall.util.PropertiesUtil;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;



/**
 * 数据源配置，可以根据需要配置多个数据源
 * @author jiawei
 * 2018年7月23日下午10:02:24
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.fjw.coolerMall.mapper")
public class DataSourceConfiguration {

    PropertiesUtil pro = new PropertiesUtil();
    
    @SuppressWarnings("static-access")
	@Bean(destroyMethod="close",name="dataSource2")
    public DataSource getDataSource() throws PropertyVetoException {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(pro.getProperty("mysql.driver"));
		dataSource.setUrl(pro.getProperty("mysql.url"));

        dataSource.setUsername(pro.getProperty("mysql.user"));
        dataSource.setPassword(pro.getProperty("mysql.password"));
        
        dataSource.setInitialSize(Integer.parseInt(
        		pro.getProperty("dataSource.initialPoolSize")));
        dataSource.setMinIdle(Integer.parseInt(
        		pro.getProperty("dataSource.minPoolSize")));
        dataSource.setMaxActive(Integer.parseInt(pro.getProperty("dataSource.maxActive")));
        dataSource.setMaxWait(Integer.parseInt(pro.getProperty("dataSource.maxWait")));
        dataSource.setTestWhileIdle(Boolean.parseBoolean(pro.getProperty("dataSource.testWhileIdle")));
        dataSource.setTimeBetweenConnectErrorMillis(Long.parseLong(
				pro.getProperty("dataSource.timeBetweenEvictionRunsMillis")));
		dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(
				pro.getProperty("dataSource.minEvictableIdleTimeMillis")));
		dataSource.setTestOnBorrow(Boolean.parseBoolean(pro.getProperty("dataSource.testOnBorrow")));
		dataSource.setTestOnReturn(Boolean.parseBoolean(pro.getProperty("dataSource.testOnReturn")));
		dataSource.setPoolPreparedStatements(Boolean.parseBoolean(pro.getProperty("dataSource.poolPreparedStatements")));
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(
				pro.getProperty("dataSource.maxPoolPreparedStatementPerConnectionSize")));

        return dataSource;
    }
    
    @Bean
    public DataSourceTransactionManager transactionManager() 
    		throws PropertyVetoException {
    	DataSourceTransactionManager dataSourceTransactionManager = 
    			new DataSourceTransactionManager(getDataSource());
    	return dataSourceTransactionManager;
    }
    
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws PropertyVetoException, IOException {
    	SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
    	sessionFactoryBean.setDataSource(getDataSource());
    	sessionFactoryBean.setFailFast(true);
    	//VendorDatabaseIdProvider vIdProvider = new VendorDatabaseIdProvider();
    	sessionFactoryBean.setTypeAliasesPackage("com.literate.service.entry.*");
    	sessionFactoryBean.setDatabaseIdProvider(vIdProvider (vendorProperties().getObject()));
    	PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    	sessionFactoryBean.setMapperLocations(resolver.getResources("mapper/*.xml"));
		return sessionFactoryBean;
    }
    
    @Bean
    public VendorDatabaseIdProvider vIdProvider(Properties properties) {
    	VendorDatabaseIdProvider vIdProvider = new VendorDatabaseIdProvider();
    	vIdProvider.setProperties(properties);
    	return vIdProvider;
    }
    
    @Bean
    public PropertiesFactoryBean vendorProperties() {
    	Properties properties = new Properties();
    	properties.put("Oracle", "oracle");
    	properties.put("MySQL", "mysql");
    	
    	PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
    	propertiesFactoryBean.setProperties(properties);
    	return propertiesFactoryBean;
    }
}
