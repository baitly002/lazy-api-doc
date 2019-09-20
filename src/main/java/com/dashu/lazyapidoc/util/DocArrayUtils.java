package com.dashu.lazyapidoc.util;

import com.dashu.lazyapidoc.process.BeanParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;

public class DocArrayUtils {
    public static final int INDEX_NOT_FOUND = -1;
    public static int indexOf(final Object[] array, final Object objectToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (objectToFind == null) {
            for (int i = startIndex; i < array.length; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else if (array.getClass().getComponentType().isInstance(objectToFind)) {
            for (int i = startIndex; i < array.length; i++) {
                if (objectToFind.equals(array[i])) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static boolean contains(final Object[] array, final Object objectToFind) {
        return indexOf(array, objectToFind, 0) != INDEX_NOT_FOUND;
    }

    public static <T> T[] add(final T[] array, final T element) {
        Class<?> type;
        if (array != null){
            type = array.getClass();
        } else if (element != null) {
            type = element.getClass();
        } else {
            throw new IllegalArgumentException("Arguments cannot both be null");
        }
        @SuppressWarnings("unchecked") // type must be T
        final
        T[] newArray = (T[]) copyArrayGrow1(array, type);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    private static Object copyArrayGrow1(final Object array, final Class<?> newArrayComponentType) {
        if (array != null) {
            final int arrayLength = Array.getLength(array);
            final Object newArray = Array.newInstance(array.getClass().getComponentType(), arrayLength + 1);
            System.arraycopy(array, 0, newArray, 0, arrayLength);
            return newArray;
        }
        return Array.newInstance(newArrayComponentType, 1);
    }
}
