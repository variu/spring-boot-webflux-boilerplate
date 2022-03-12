package common;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import com.navercorp.fixturemonkey.mockito.arbitrary.MockitoInterfaceSupplier;

public abstract class FixtureMonkeyUtils {
  public static FixtureMonkey monkey() {
    return FixtureMonkey.builder()
        .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
        .defaultInterfaceSupplier(MockitoInterfaceSupplier.INSTANCE)
        .build();
  }
}
