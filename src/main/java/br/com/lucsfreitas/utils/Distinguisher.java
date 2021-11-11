package br.com.lucsfreitas.utils;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Distinguisher {

    /**
     * Compare two Strings ignoring case and accents
     *
     * @param str1 value to compare
     * @param str2 value to compare
     * @return true if equals, or false in other-case
     */
    public static boolean equalsIgnoreCaseAndAccents(String str1, String str2) {
        if (str1 == null && str2 == null){
            return true;
        }
        else if(str1 == null || str2 == null) {
            return false;
        }
        return deAccent(str1).equalsIgnoreCase(deAccent(str2));
    }

    /**
     * Verify if a collection contains duplicated values, based on informed keyExtractors
     *
     * @param collection the collection to verify
     * @param keyExtractors the functions used to verify duplicated
     * @param <T>
     * @return true if contains duplicated values, or false in other-case
     */
    @SafeVarargs
    public static <T> boolean verifyDuplicates (Collection<T> collection, Function<? super T, ?>... keyExtractors) {
        long countDistinct = collection.stream()
                .filter(distinctBy(keyExtractors))
                .count();
        return countDistinct != collection.size();
    }

    /**
     * Filter distinct values. Can be used on manipulation of Streams.
     *
     * @param keyExtractors the functions used to verify duplicated
     * @param <T>
     * @return
     */
    @SafeVarargs
    public static <T> Predicate<T> distinctBy(Function<? super T, ?>... keyExtractors) {
        final Map<List<?>, Boolean> seen = new ConcurrentHashMap<>();

        return t -> {
            final List<?> keys = Arrays.stream(keyExtractors)
                    .map(ke -> parseUpper(ke.apply(t)))
                    .collect(Collectors.toList());

            return seen.putIfAbsent(keys, Boolean.TRUE) == null;
        };
    }

    private static Object parseUpper(Object value) {
        if (value instanceof String) {
            return deAccent(((String) value).toUpperCase());
        }
        return value;
    }

    /**
     * Remove accents of String
     *
     * @param str original String
     * @return String without accents
     */
    public static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
}
