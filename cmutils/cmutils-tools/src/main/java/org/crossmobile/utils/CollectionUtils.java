/* (c) 2019 by Panayotis Katsaloulis
 *
 * CrossMobile is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 2.
 *
 * CrossMobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CrossMobile; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.crossmobile.utils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.crossmobile.utils.CollectionUtils.IteratorHandler.EMPTY;
import static org.crossmobile.utils.CollectionUtils.IteratorHandler.IterationCode.*;

public class CollectionUtils {

    public static <T> List<T> asList(T... items) {
        return items == null || items.length < 1 ? Collections.EMPTY_LIST : Arrays.asList(items);
    }

    public static <T> List<T> asList(Collection<T> collection) {
        return collection instanceof List
                ? (List<T>) collection
                : new ArrayList<>(collection);
    }

    public static <T> List<T> asList(Map<?, List<T>> map) {
        if (map == null || map.isEmpty())
            return Collections.emptyList();
        List<T> collective = new ArrayList<>();
        for (Object key : map.keySet())
            collective.addAll(map.get(key));
        return collective;
    }

    public static <S, T> List<T> asList(Iterable<S> input, Function<S, T> converter, Predicate<T> acceptance) {
        if (input == null || converter == null)
            return null;
        List<T> result = new ArrayList<>();
        for (S item : input) {
            T out = converter.apply(item);
            if (acceptance == null || acceptance.test(out))
                result.add(converter.apply(item));
        }
        return result;
    }

    public static <S, T> List<T> asList(Iterable<S> input, Function<S, T> converter) {
        return asList(input, converter, null);
    }

    public static <S, T> Iterable<T> asIterable(Function<S, T> converter, Iterable<S> input) {
        return input == null || converter == null
                ? null
                : () -> joinIterator(converter, input.iterator());
    }

    public static <T> Collection<T> asCollection(Iterable<T>... iterables) {
        return iterables == null ? null
                : iterables.length == 1 && iterables[0] != null && iterables[0] instanceof Collection
                ? (Collection<T>) iterables[0]
                : asCollection(Arrays.asList(iterables));
    }

    public static <T> Collection<T> asCollection(Iterable<? extends Iterable<T>> iterables) {
        return asCollection(ArrayList.class, iterables);
    }

    public static <T> Collection<T> asCollection(Class<? extends Collection> classtype, Iterable<T>... iterables) {
        return asCollection(Arrays.asList(iterables));
    }

    public static <T> Collection<T> asCollection(Class<? extends Collection> classtype, Iterable<? extends Iterable<T>> iterables) {
        if (classtype == null)
            return asCollection(iterables);
        try {
            Collection<T> c = classtype.newInstance();
            if (iterables != null)
                for (Iterable<T> iterable : iterables)
                    if (iterable != null)
                        for (T item : iterable)
                            c.add(item);
            return c;
        } catch (InstantiationException | IllegalAccessException ex) {
            return null;
        }
    }

    public static <T> Collection<T> asCollection(Collection<T>... parts) {
        return asCollection(Arrays.asList(parts));
    }

    public static <T> Collection<T> asCollection(Collection<? extends Collection<T>> parts) {
        AtomicInteger totalsize = new AtomicInteger();
        if (parts != null)
            for (Collection<T> part : parts)
                if (part != null)
                    totalsize.addAndGet(part.size());
        if (totalsize.get() == 0)
            return Collections.EMPTY_LIST;

        return new AbstractCollection<T>() {
            @Override
            public Iterator<T> iterator() {
                Collection<Iterator<? extends T>> iterators = new ArrayList<>();
                for (Collection<T> c : parts)
                    if (c != null)
                        iterators.add(c.iterator());
                return joinIterator(iterators);
            }

            @Override
            public int size() {
                return totalsize.get();
            }
        };
    }

    public static <T> Collection<T> asCollection(Class<? extends Collection> classtype, Collection<T>... parts) {
        return asCollection(classtype, Arrays.asList(parts));
    }

    public static <T> Collection<T> asCollection(Class<? extends Collection> classtype, Collection<? extends Collection<T>> parts) {
        if (classtype == null)
            return asCollection(parts);
        try {
            Collection<T> c = classtype.newInstance();
            if (parts != null)
                for (Collection<T> part : parts)
                    if (part != null)
                        c.addAll(part);
            return c;
        } catch (InstantiationException | IllegalAccessException ex) {
            return null;
        }
    }

    public static <T> boolean containsAny(Collection<T> a, Collection<T> b) {
        for (T item : b)
            if (a.contains(item))
                return true;
        return false;
    }

    public static <T> T getWithCondition(Collection<T> a, Predicate<T> p){
        for (T t : a) {
            if(p.test(t))
                return t;
        }
        return null;
    }

    public static String minimumText(Set<String> input) {
        if (input == null)
            return null;
        if (input.isEmpty())
            return "";
        Iterator<String> iterator = input.iterator();
        String current = iterator.next();
        while (iterator.hasNext())
            current = TextUtils.commonText(current, iterator.next());
        return current;
    }

    public static <K, V> K keyFromValue(Map<K, V> map, V value) {
        for (K key : map.keySet())
            if (Objects.equals(map.get(key), value))
                return key;
        return null;
    }

    public static <T> T head(Iterable<T> collection) {
        Iterator<T> it = collection == null ? null : collection.iterator();
        return it != null && it.hasNext() ? it.next() : null;
    }

    public static <T> List<T> tail(Iterable<T> collection) {
        if (collection == null)
            return Collections.EMPTY_LIST;
        List<T> result = (collection instanceof List) ? (List) collection : asList(asCollection(collection));
        if (result.size() < 2)
            return Collections.EMPTY_LIST;
        return result.subList(1, result.size());
    }

    public static <T> List<T> front(Iterable<T> collection) {
        if (collection == null)
            return Collections.EMPTY_LIST;
        List<T> result = (collection instanceof List) ? (List) collection : asList(asCollection(collection));
        if (result.size() < 2)
            return Collections.EMPTY_LIST;
        return result.subList(0, result.size() - 1);
    }

    public static <T> T last(Iterable<T> collection) {
        if (collection == null)
            return null;
        T last = null;
        Iterator<T> it = collection.iterator();
        while (it.hasNext())
            last = it.next();
        return last;
    }

    public static <T> IteratorHandler<T> forEach(Collection<T> collection) {
        return collection != null && !collection.isEmpty()
                ? new IteratorHandler<>(collection)
                : EMPTY;
    }

    public static <T> IteratorHandler<T> forEach(T[] collection) {
        return collection != null && collection.length > 0
                ? new IteratorHandler<>(Arrays.asList(collection))
                : EMPTY;
    }

    public static <T> Iterator<T> joinIterator(Iterator<? extends T>... iterators) {
        return joinIterator((T t) -> t, iterators);
    }

    public static <S, T> Iterator<T> joinIterator(Function<S, T> converter, Iterator<? extends S>... iterators) {
        return joinIterator(converter, Arrays.asList(iterators));
    }

    public static <T> Iterator<T> joinIterator(Collection<Iterator<? extends T>> iterators) {
        return joinIterator(t -> t, iterators);
    }

    public static <S, T> Iterator<T> joinIterator(Function<S, T> converter, Collection<Iterator<? extends S>> iterators) {
        if (converter == null || iterators == null)
            return null;
        return new Iterator<T>() {
            ArrayList<Iterator<? extends S>> list = new ArrayList<>();

            {
                for (Iterator<? extends S> it : iterators)
                    if (it != null && it.hasNext())
                        list.add(it);
            }

            @Override
            public boolean hasNext() {
                return list.size() > 0 && list.get(0).hasNext();
            }

            @Override
            public T next() {
                S next = list.get(0).next();
                if (!list.get(0).hasNext())
                    list.remove(0);
                return converter.apply(next);
            }
        };
    }

    public static <T> Iterable<T> filter(Iterable<T> source, Predicate<T> filter) {
        return () -> filter(source.iterator(), filter);
    }

    public static <T> Iterator<T> filter(Iterator<T> source, Predicate<T> filter) {
        if (filter == null)
            return source;
        return new Iterator<T>() {
            private T last;

            @Override
            public boolean hasNext() {
                while (source.hasNext()) {
                    last = source.next();
                    if (filter.test(last))
                        return true;
                }
                return false;
            }

            @Override
            public T next() {
                return last;
            }
        };
    }

    public static final class IteratorHandler<T> {

        static final IteratorHandler EMPTY = new IteratorHandler(Arrays.asList());

        public interface ConsumerEx<T> {

            void accept(T t) throws Throwable;
        }

        static final class IterationCode<T> {

            static final int ANY = 0;
            static final int HEAD = 1;
            static final int TAIL = 2;
            static final int FRONT = 3;
            static final int LAST = 4;

            private final int where;
            private final ConsumerEx<T> code;

            public IterationCode(int life, ConsumerEx<T> code) {
                this.where = life;
                this.code = code;
            }

        }

        private final Collection<T> collection;
        private final Collection<IterationCode<T>> code = new ArrayList<>();
        private Predicate<T> predicate;

        public IteratorHandler(Collection<T> collection) {
            this.collection = collection;
        }

        public IteratorHandler<T> setFilter(Predicate<T> predicate) {
            this.predicate = predicate;
            return this;
        }

        public IteratorHandler<T> onAny(ConsumerEx<T> exec) {
            if (exec != null)
                code.add(new IterationCode<>(ANY, exec));
            return this;
        }

        public IteratorHandler<T> onHead(ConsumerEx<T> exec) {
            if (exec != null)
                code.add(new IterationCode<>(HEAD, exec));
            return this;
        }

        public IteratorHandler<T> onTail(ConsumerEx<T> exec) {
            if (exec != null)
                code.add(new IterationCode<>(TAIL, exec));
            return this;
        }

        public IteratorHandler<T> onFront(ConsumerEx<T> exec) {
            if (exec != null)
                code.add(new IterationCode<>(FRONT, exec));
            return this;
        }

        public IteratorHandler<T> onLast(ConsumerEx<T> exec) {
            if (exec != null)
                code.add(new IterationCode<>(LAST, exec));
            return this;
        }

        public <E extends Throwable> void go() throws E {
            try {
                int count = 0;
                boolean first = true;
                for (T item : collection)
                    if (predicate == null || predicate.test(item)) {
                        count++;
                        boolean last = count == collection.size();
                        for (IterationCode<T> it : code)
                            if (it.where == ANY || (first && it.where == HEAD) || (!first && it.where == TAIL) || (last && it.where == LAST) || (!last && it.where == FRONT))
                                it.code.accept(item);
                        first = false;
                    }
            } catch (Throwable ex) {
                throw (E) ex;
            }
        }
    }
}