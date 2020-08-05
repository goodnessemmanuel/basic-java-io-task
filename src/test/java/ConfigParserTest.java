import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigParserTest {
    ConfigParser config;

    @BeforeEach
    void init ()
    {
        config = new ConfigParser(); //when no parameter is passed
    }

    @DisplayName("Check if key returns the correct value")
    @Test
    void shouldReturnCorrectKeyValue()
    {
        String expected = "fintek";
        String actual =  config.get("name");
        config = new ConfigParser("config-dev.txt"); //with a different argument
        String expected1 = "fintek-development";
        String actual1 =  config.get("name");
        assertAll(
                () -> assertEquals(expected, actual, "should return correct value for the provided key"),
                () -> assertEquals(expected1, actual1, "should return correct value for the provided key")

        );
    }

}