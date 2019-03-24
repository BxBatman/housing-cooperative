package config;

import model.Authorities;
import model.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = {@ComponentScan("repository"), @ComponentScan("service"), @ComponentScan({"config"})})
@EnableJpaRepositories(basePackages = "repository")
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource getDataSource(){
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(environment.getRequiredProperty("h2.driver"));
        dataSource.setUrl(environment.getRequiredProperty("h2.jdbcUrl"));
        dataSource.setUsername(environment.getRequiredProperty("h2.username"));
        dataSource.setPassword(environment.getRequiredProperty("h2.password"));

        return dataSource;

    }



    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(getDataSource());
        entityManagerFactoryBean.setPackagesToScan("model");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setJpaProperties(getProperties());

        return entityManagerFactoryBean;
    }





    @Bean
    public Properties getProperties() {


        Properties props = new Properties();

        props.put(SHOW_SQL, environment.getProperty("hibernate.show_sql"));
        props.put(HBM2DDL_AUTO, environment.getProperty("hibernate.hbm2ddl.auto"));
        props.put(DIALECT,environment.getProperty("hibernate.dialect"));

        return props;
    }

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }
}
