/*
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
        assertEquals(original, decoded, "La contraseña decodificada debería coincidir con la original.");
    }

    @Test
    public void testEncodeAndDecodeEmptyString() {
        String original = "";
        String encoded = AlmacenDeDatos.encode(original);
        String decoded = AlmacenDeDatos.decode(encoded);
        assertEquals(original, decoded, "El proceso de codificación y decodificación de una cadena vacía debería funcionar.");
    }

    @Test
    public void testEncodeAndDecodeSpecialCharacters() {
        String original = "!@#$%^&*()_+";
        String encoded = AlmacenDeDatos.encode(original);
        String decoded = AlmacenDeDatos.decode(encoded);
        assertEquals(original, decoded, "El proceso de codificación y decodificación debería manejar correctamente los caracteres especiales.");
    }

    @Test
    public void testDistinctEncodeOutputs() {
        String string1 = "hola123";
        String string2 = "adios123";
        String encoded1 = AlmacenDeDatos.encode(string1);
        String encoded2 = AlmacenDeDatos.encode(string2);
        assertNotEquals(encoded1, encoded2, "Dos cadenas diferentes no deberían codificarse de la misma manera.");
    }
}

 */

