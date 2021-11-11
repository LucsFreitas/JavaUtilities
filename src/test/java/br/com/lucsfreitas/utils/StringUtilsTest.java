package br.com.lucsfreitas.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StringUtilsTest {

    @ParameterizedTest
    @CsvSource({"123,123", "1a2b3c,123", "   123  ,123"})
    void should_removeNonNumericCharacter(String input, String expected) {
        Assertions.assertEquals(expected, StringUtils.removeNonNumericCharacter(input));
    }

    @Test
    void should_dontFailWithNull () {
        Assertions.assertNull(StringUtils.removeNonNumericCharacter(null));
    }

}
