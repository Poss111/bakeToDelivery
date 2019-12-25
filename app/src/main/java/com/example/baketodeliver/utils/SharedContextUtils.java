package com.example.baketodeliver.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is a temporary fix for the shared Data sources.
 *
 * @author Daniel Poss
 */
public class SharedContextUtils {

    /** Shared Map of objects **/
    private static Map<String, Object> sharedMap = new HashMap<>();

    private SharedContextUtils() {
        super();
    }

    /** Used to persist objects to the Map via a String key
     *
     * @param key - String key to be used to reference an Object by
     * @param objectToPersist - The Object to be persisted under the given key
     */
    public static void putObjectToMap(String key, Object objectToPersist) {
        sharedMap.put(key, objectToPersist);
    }

    /**
     * Used to access objects from the shared Map via a String key
     *
     * @param key - String key to be used to reference an Object by
     * @return The Object stored under the given String key
     */
    public static Object getObjectFromMap(String key) {
        return sharedMap.get(key);
    }

}
