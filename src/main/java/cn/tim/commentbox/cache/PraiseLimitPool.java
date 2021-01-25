package cn.tim.commentbox.cache;

import com.google.common.collect.Lists;

import java.util.List;

public class PraiseLimitPool {
    // 缓存1024个用户
    private static FIFOCache<String, List<Integer>> cache = new FIFOCache<>(1024);
    private static FIFOCache<String, List<Integer>> cacheChild = new FIFOCache<>(1024);

    public static void insertCache(String clientId, Integer commentId){
        List<Integer> retList = cache.get(clientId);
        if(retList == null){
            retList = Lists.newArrayList();
        }
        retList.add(commentId);
        cache.put(clientId, retList);
    }

    /**
     * true 可以点赞
     */
    public static boolean find(String clientId, Integer commentId){
        List<Integer> list = cache.get(clientId);
        if(list == null) return true;
        return !list.contains(commentId);
    }


    public static void insertCacheChild(String clientId, Integer commentId){
        List<Integer> retList = cacheChild.get(clientId);
        if(retList == null){
            retList = Lists.newArrayList();
        }
        retList.add(commentId);
        cacheChild.put(clientId, retList);
    }

    /**
     * true 可以点赞
     */
    public static boolean findChild(String clientId, Integer commentId){
        List<Integer> list = cacheChild.get(clientId);
        if(list == null) return true;
        return !list.contains(commentId);
    }
}
