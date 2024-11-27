package concertmanagement.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import concertmanagementServiceImpl.VenueServiceImpl;

public class VenueServiceImplTest {
VenueServiceImpl serv = new VenueServiceImpl();
    
    @ParameterizedTest
  
    @ValueSource(ints= {1500,1690,1000})
    void testcheckVenueCapacityException(int capacity) {
        int actual = serv.testcheckVenueCapacityException(capacity);
        Assertions.assertEquals(1, actual, "Capacity should be less than or equal to 1500");
    }
}
