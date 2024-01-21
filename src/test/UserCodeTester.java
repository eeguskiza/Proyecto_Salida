import org.junit.Test;
import org.proyectosalida.Datos.AlmacenDeDatos;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UserCodeTester {

    @Test
    public void testEncodeAndDecode() {
        String original = "miContraseña123";
        String encoded = AlmacenDeDatos.encode(original);
        String decoded = AlmacenDeDatos.decode(encoded);
        assertEquals("La contraseña decodificada debería coincidir con la original.",original, decoded );
    }

    @Test
    public void testEncodeAndDecodeEmptyString() {
        String original = "";
        String encoded = AlmacenDeDatos.encode(original);
        String decoded = AlmacenDeDatos.decode(encoded);
        assertEquals("El proceso de codificación y decodificación de una cadena vacía debería funcionar.", original, decoded);
    }

    @Test
    public void testEncodeAndDecodeSpecialCharacters() {
        String original = "!@#$%^&*()_+";
        String encoded = AlmacenDeDatos.encode(original);
        String decoded = AlmacenDeDatos.decode(encoded);
        assertEquals("El proceso de codificación y decodificación debería manejar correctamente los caracteres especiales.", original, decoded);
    }

    @Test
    public void testDistinctEncodeOutputs() {
        String string1 = "hola123";
        String string2 = "adios123";
        String encoded1 = AlmacenDeDatos.encode(string1);
        String encoded2 = AlmacenDeDatos.encode(string2);
        assertNotEquals("Dos cadenas diferentes no deberían codificarse de la misma manera.", encoded1, encoded2);
    }
}


