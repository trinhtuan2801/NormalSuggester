package Baitap;

import java.util.HashMap;

public class HashMap2D {
    private HashMap<String,HashMap<String,Integer>> outerMap = new HashMap<String,HashMap<String,Integer>>();

    public void addKey(String key) {
        HashMap<String,Integer> i = new HashMap<String,Integer>();
        outerMap.put(key, i);
    }

    public void addValue(String key1, String key2, int value) {
        HashMap<String, Integer> innerMap = outerMap.get(key1);
        innerMap.put(key2, value);
        outerMap.put(key1, innerMap);
    }

    public HashMap<String,HashMap<String,Integer>> getOuter(){
        return outerMap;
    }

    public HashMap<String, Integer> getInner(String key){
        return outerMap.get(key);
    }

}

