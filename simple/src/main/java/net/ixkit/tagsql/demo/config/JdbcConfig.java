package net.ixkit.tagsql.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan("net.ixkit.tagsql.demo")
public class JdbcConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
          .setType(EmbeddedDatabaseType.H2)
          .addScript("classpath:jdbc/init/db-schema.sql")
          .addScript("classpath:jdbc/init/fixtures-data.sql")
          .build();
    }

    //@Bean
    public DataSource mysqlDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/tagsql?useUnicode=true&characterEncoding=UTF-8&userSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }

}