import org.junit.Test;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

import static play.test.Helpers.*;
import static org.junit.Assert.*;

public class FooTest extends WithApplication {

    @Test
    public void testFooRoute() {
        Result result = route(fakeRequest(GET, "/foo"));
        assertNotNull(result);
    }

}