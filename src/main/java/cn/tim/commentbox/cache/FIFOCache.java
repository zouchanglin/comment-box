package cn.tim.commentbox.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 固定大小的先进先出缓存
 * @param <K>
 * @param <V>
 */
public class FIFOCache<K, V> extends LinkedHashMap<K, V> {
    private static int MAX_CACHE_SIZE;

    public FIFOCache(int maxCacheSize){
        super(maxCacheSize, 0.75f, false);
        MAX_CACHE_SIZE = maxCacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return MAX_CACHE_SIZE < size();
    }
}
