package br.com.lucsfreitas.utils;

import br.com.lucsfreitas.domain.enums.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnumConverterTest {

    @Test
    void should_convertToEnum() {
        Assertions.assertEquals(Gender.MALE, EnumConverter.convertToEnum(Gender.class, "MALE"));
    }

    @Test
    void should_fail() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> EnumConverter.convertToEnum(Gender.class, "MALE123"));
    }

    @Test
    void should_dontFailWithNull () {
        Assertions.assertNull(EnumConverter.convertToEnum(Gender.class, null));
    }
}
