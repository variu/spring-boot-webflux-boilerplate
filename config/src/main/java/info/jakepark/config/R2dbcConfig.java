package info.jakepark.config;

import info.jakepark.properties.DataBaseProperties;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration(proxyBeanMethods = false)
@EnableTransactionManagement
@EnableR2dbcRepositories(basePackages = {"info.jakepark.**.repository"})
public class R2dbcConfig extends AbstractR2dbcConfiguration {

  private final DataBaseProperties dataBaseProperties;
  private final R2dbcProperties r2dbcProperties;

  public R2dbcConfig(DataBaseProperties dataBaseProperties, R2dbcProperties r2dbcProperties) {
    this.dataBaseProperties = dataBaseProperties;
    this.r2dbcProperties = r2dbcProperties;
  }

  @Override
  public ConnectionFactory connectionFactory() {
    String url = dataBaseProperties.url();
    ConnectionFactory connectionFactory = ConnectionFactories.get(url);

    if (r2dbcProperties.getPool().isEnabled()) {
      ConnectionPoolConfiguration configuration =
          ConnectionPoolConfiguration.builder()
              .connectionFactory(connectionFactory)
              .initialSize(r2dbcProperties.getPool().getInitialSize())
              .maxSize(r2dbcProperties.getPool().getMaxSize())
              .validationQuery(r2dbcProperties.getPool().getValidationQuery())
              .build();

      return new ConnectionPool(configuration);
    } else {
      return connectionFactory;
    }
  }
}
