package common;

import config.R2dbcAuditingConfig;
import config.R2dbcConfig;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import properties.DataBaseProperties;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@DataR2dbcTest
@Import({R2dbcConfig.class, R2dbcAuditingConfig.class})
@EnableConfigurationProperties({DataBaseProperties.class, R2dbcProperties.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public @interface CustomDataR2dbcTest {}
