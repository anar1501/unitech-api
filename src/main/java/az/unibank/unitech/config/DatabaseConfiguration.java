package az.unibank.unitech.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "az.unibank.unitech.data.repository",
        entityManagerFactoryRef = "unitech-em",
        transactionManagerRef = "unitech-tm"
)
public class DatabaseConfiguration {

    @Value("${unitech-api.datasource.username}")
    private String datasourceUser;
    @Value("${unitech-api.datasource.password}")
    private String datasourcePassword;
    @Value("${unitech-api.datasource.url}")
    private String datasourceHost;
    @Value("${unitech-api.datasource.driver}")
    private String datasourceDriver;
    @Value("${unitech-api.hibernate.database-platform}")
    private String hibernateDatabasePlatform;
    @Value("${unitech-api.hibernate.show-sql}")
    private boolean hibernateShowSql;
    @Value("${unitech-api.hibernate.format-sql}")
    private boolean hibernateFormatSql;
    @Value("${unitech-api.hibernate.ddl-auto}")
    private String hibernateDdlAuto;

    @Bean
    public DataSource dataSourceSphere() {
        HikariConfig config = new HikariConfig();
        config.setUsername(datasourceUser);
        config.setPassword(datasourcePassword);
        config.setJdbcUrl(datasourceHost);
        config.setDriverClassName(datasourceDriver);
        return new HikariDataSource(config);
    }

    @Bean(name = "unitech-em")
    public LocalContainerEntityManagerFactoryBean entityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSourceSphere());
        em.setPackagesToScan("az.unibank.unitech.data.entity");
        em.setJpaDialect(new HibernateJpaDialect());
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", hibernateDdlAuto);
        properties.put("hibernate.dialect", hibernateDatabasePlatform);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("hibernate.format_sql", hibernateFormatSql);
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean(name = "unitech-tm")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManager().getObject());
        return transactionManager;
    }

}