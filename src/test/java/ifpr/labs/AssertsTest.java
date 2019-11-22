package ifpr.labs;

import ifpr.models.Usuario;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AssertsTest {

    @Test
    public void test(){
        assertTrue(true);
        assertFalse(false);

        assertEquals(2,2);

        Integer n1 = 10;
        int n2 = 10;

        assertEquals(n1.intValue(), n2);
        assertEquals(n1, Integer.valueOf(n2));

        assertEquals(3.0, 3.001, 0.001);


        Usuario u1 = new Usuario("João Marcos");
        Usuario u2 = new Usuario("João Marcos");

        assertEquals(u1, u2);






    }

}
