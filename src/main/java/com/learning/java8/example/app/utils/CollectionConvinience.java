package com.learning.java8.example.app.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionConvinience implements Serializable {

    private static final long serialVersionUID = -3680516498805890936L;



    public static <T> boolean isUniqueResult(Collection<T> collection){
        return isNotNullNorEmpty(collection) && (collection.size() == 1);
    }

    public static boolean isNotNullNorEmpty(Collection<?> collection){
        return collection != null && !collection.isEmpty();
    }

    @SafeVarargs
    public static <T> boolean isNotNullNorEmpty(T ... args){
        return args != null && args.length > 0;
    }

    public static boolean isNotNullNorEmpty(Map<?,?> map){
        return map != null && !map.isEmpty();
    }

    public static boolean isNullOrEmpty(Collection<?> collection){
        return collection == null || collection.isEmpty();
    }

    @SafeVarargs
    public static <T> boolean isNullOrEmpty(T ... args){
        return args == null || args.length == 0;
    }

    public static boolean isNullOrEmpty(Map<?,?> map){
        return map == null || map.isEmpty();
    }

    @SafeVarargs
    public static <T> List<T> asList(T ... items){
        List<T> list = new ArrayList<T>();
        if(isNotNullNorEmpty(items))
            Collections.addAll(list, items);
        return list;
    }

    public static <T> List<T> asList(Collection<T> collection){
        if (collection != null)
            return new ArrayList<T>(collection);
        else
            return new ArrayList<T>();
    }

    @SafeVarargs
    public static <T> Set<T> asSet(T ... items){
        Set<T> set = new HashSet<T>();
        if(isNotNullNorEmpty(items))
            Collections.addAll(set, items);
        return set;
    }

    @SafeVarargs
    public static <T> Set<T> asLinkedSet(T ... items){
        Set<T> set = new LinkedHashSet<T>();
        if(isNotNullNorEmpty(items))
            Collections.addAll(set, items);
        return set;
    }

    public static <T> Set<T> asSet(Collection<T> collection){
        if (collection != null)
            return new HashSet<T>(collection);
        else
            return new HashSet<T>();
    }

    @SafeVarargs
    public static <T> List<T> asReadOnlyList(T ... items) {
        return asReadOnlyList(asList(items));
    }

    public static <T> List<T> asReadOnlyList(List<T> list) {
        if (list != null)
            return Collections.unmodifiableList(list);
        else
            return Collections.emptyList();
    }

    public static <T> List<T> asReadOnlyList(Collection<T> set) {
        return asReadOnlyList(asList(set));
    }

    @SafeVarargs
    public static <T> Set<T> asReadOnlySet(T ... items) {
        return asReadOnlySet(asSet(items));
    }

    public static <T> Set<T> asReadOnlySet(Set<T> set) {
        if (set != null)
            return Collections.unmodifiableSet(set);
        else
            return Collections.emptySet();
    }

    public static <T> Set<T> asReadOnlySet(Collection<T> list) {
        return asReadOnlySet(asSet(list));
    }

    public static <K,V> Map<K,V> asReadOnlyMap(Map<K,V> map) {
        if (map != null)
            return Collections.unmodifiableMap(map);
        else
            return Collections.emptyMap();
    }

    public static <K,V> Maplet<K, V> maplet(K key, V val) {
        return new Maplet<K, V>(key, val);
    }

    public static <K,V> Maplet<K, V> m(K key, V val) {
        return maplet(key, val);
    }

    @SafeVarargs
    public static <K,V> Map<K,V> asMap(Maplet<K,V> ... maplets) {
        Map<K,V> map;
        if(isNotNullNorEmpty(maplets)){
            map = new HashMap<K,V>();
            for(Maplet<K,V> maplet : maplets)
                map.put(maplet.key, maplet.val);
        } else {
            map = Collections.emptyMap();
        }
        return map;
    }

    public static class Maplet<K,V> implements Serializable {
        private static final long serialVersionUID = -8254125014052726028L;

        public K key;
        public V val;

        public Maplet(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public static <T> Set<T> substract(Collection<T> a, Collection<T> b) {
        Set<T> temp = asSet(a);
        temp.removeAll(b);
        return temp;
    }

    public static <T> Set<T> intersect(Collection<T> a, Collection<T> b) {
        Set<T> temp = asSet(a);
        temp.retainAll(b);
        return temp;
    }

}
