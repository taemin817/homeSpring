package org.zerock.config;

import javax.sql.DataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages= {"org.zerock.sample"})
@MapperScan(basePackages= {"org.zerock.config"})
public class RootConfig{

	@Bean(destroyMethod = "close")
	public DataSource dataSource(){
	    HikariConfig hikariConfig = new HikariConfig();
//	    hikariConfig.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//	    hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:ptm"); 
	    hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
	    hikariConfig.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@localhost:1521:ptm"); 
	    hikariConfig.setUsername("scott");
	    hikariConfig.setPassword("tiger");
	 
	    HikariDataSource dataSource = new HikariDataSource(hikariConfig);
	 
	    return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		return (SqlSessionFactory)sqlSessionFactory.getObject();
	}

}
