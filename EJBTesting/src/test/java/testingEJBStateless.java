import org.example.EJBClient;
import org.example.EJBClient2;
import org.example.StatelessEJB;
import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;

import jakarta.inject.Inject;

@ExtendWith(WeldJunit5Extension.class)
@EnableWeld
public class testingEJBStateless {

    @WeldSetup
    public WeldInitiator weldInitiator =
            WeldInitiator.from( EJBClient.class, EJBClient2.class, StatelessEJB.class ).build();

    @Inject
    private EJBClient ejbClient;

    @Inject
    private EJBClient2 ejbClient2;

    @Test
    public void givenOneStatelessBean_whenStateissetinOneBean_secondStateisAlsoSet() {
        ejbClient.statelessEJB.name = "Client1";
//        ejbClient2.statelessEJB.name = "Client2";

        Assertions.assertEquals("Client1", ejbClient.statelessEJB.name, "Client 1 didn't Match");
        Assertions.assertEquals("Client1", ejbClient2.statelessEJB.name, "Client 2 didn't Match");
    }

}
