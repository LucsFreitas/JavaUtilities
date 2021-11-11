package br.com.lucsfreitas.utils;

public class EnumConverter {

    /**
     * Convert a value into enum value
     *
     * @param destinationClassType EnumType
     * @param sourceEnum value to convert
     * @return enum value
     */
    public static <T extends Enum<T>> T convertToEnum(final Class<T> destinationClassType, String sourceEnum){
        if (sourceEnum == null) {
            return null;
        }
        return T.valueOf(destinationClassType, sourceEnum);
    }
}
