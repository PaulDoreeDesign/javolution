/*
 * Javolution - Java(TM) Solution for Real-Time and Embedded Systems
 * Copyright (C) 2012 - Javolution (http://javolution.org/)
 * All rights reserved.
 * 
 * Permission to use, copy, modify, and distribute this software is
 * freely granted, provided that this notice is preserved.
 */
package org.javolution.util.internal.set;

import java.util.Collection;
import java.util.Iterator;

import org.javolution.util.FastSet;
import org.javolution.util.function.BinaryOperator;
import org.javolution.util.function.Consumer;
import org.javolution.util.function.Order;
import org.javolution.util.function.Predicate;
import org.javolution.util.internal.ReadWriteLockImpl;

/**
 * A shared view over a set (reads-write locks).
 */
public final class SharedSetImpl<E> extends FastSet<E> {

    private static final long serialVersionUID = 0x700L; // Version.
    private final FastSet<E> inner;
    private final ReadWriteLockImpl lock;

    public SharedSetImpl(FastSet<E> inner) {
        this.inner = inner;
        this.lock = new ReadWriteLockImpl();
    }

    public SharedSetImpl(FastSet<E> inner, ReadWriteLockImpl lock) {
        this.inner = inner;
        this.lock = lock;
    }

    @Override
    public boolean add(E element) {
        lock.writeLock.lock();
        try {
            return inner.add(element);
        } finally {
            lock.writeLock.unlock();
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> that) {
        lock.writeLock.lock();
        try {
            return inner.addAll(that);
        } finally {
            lock.writeLock.unlock();
        }
    }

    @Override
    public boolean addAll(E... elements) {
        lock.writeLock.lock();
        try {
            return inner.addAll(elements);
        } finally {
            lock.writeLock.unlock();
        }
    }

    @Override
    public E any() {
        lock.readLock.lock();
        try {
            return inner.any();
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public E ceiling(E element) {
        lock.readLock.lock();
        try {
            return inner.ceiling(element);
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public void clear() {
        lock.writeLock.lock();
        try {
            inner.clear();
        } finally {
            lock.writeLock.unlock();
        }
    }

    @Override
    public SharedSetImpl<E> clone() {
        lock.readLock.lock();
        try {
            return new SharedSetImpl<E>(inner.clone());
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public Order<? super E> order() {
        return inner.order(); // Immutable.
    }

    @Override
    public boolean contains(final Object searched) {
        lock.readLock.lock();
        try {
            return inner.contains(searched);
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public boolean containsAll(Collection<?> that) {
        lock.readLock.lock();
        try {
            return inner.containsAll(that);
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public Iterator<E> descendingIterator() {
        lock.readLock.lock();
        try {
            return inner.clone().unmodifiable().descendingIterator();
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public Iterator<E> descendingIterator(E fromElement) {
        lock.readLock.lock();
        try {
            return inner.clone().unmodifiable().descendingIterator(fromElement);
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public boolean equals(Object obj) {
        lock.readLock.lock();
        try {
            return inner.equals(obj);
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public E first() {
        lock.readLock.lock();
        try {
            return inner.first();
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public E floor(E element) {
        lock.readLock.lock();
        try {
            return inner.floor(element);
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public void forEach(Consumer<? super E> consumer) {
        lock.readLock.lock();
        try {
            inner.forEach(consumer);
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public int hashCode() {
        lock.readLock.lock();
        try {
            return inner.hashCode();
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public E higher(E element) {
        lock.readLock.lock();
        try {
            return inner.higher(element);
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        lock.readLock.lock();
        try {
            return inner.isEmpty();
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public Iterator<E> iterator() {
        lock.readLock.lock();
        try {
            return inner.clone().unmodifiable().iterator();
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public Iterator<E> iterator(E fromElement) {
        lock.readLock.lock();
        try {
            return inner.clone().unmodifiable().iterator(fromElement);
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public E last() {
        lock.readLock.lock();
        try {
            return inner.last();
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public E lower(E element) {
        lock.readLock.lock();
        try {
            return inner.lower(element);
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public E max() {
        lock.readLock.lock();
        try {
            return inner.max();
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public E min() {
        lock.readLock.lock();
        try {
            return inner.min();
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public E pollFirst() {
        lock.readLock.lock();
        try {
            return inner.pollFirst();
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public E pollLast() {
        lock.readLock.lock();
        try {
            return inner.pollLast();
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public E reduce(BinaryOperator<E> operator) {
        lock.readLock.lock();
        try {
            return inner.reduce(operator);
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public boolean remove(Object searched) {
        lock.writeLock.lock();
        try {
            return inner.remove(searched);
        } finally {
            lock.writeLock.unlock();
        }
    }

    @Override
    public boolean removeAll(Collection<?> that) {
        lock.writeLock.lock();
        try {
            return inner.removeAll(that);
        } finally {
            lock.writeLock.unlock();
        }
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        lock.writeLock.lock();
        try {
            return inner.removeIf(filter);
        } finally {
            lock.writeLock.unlock();
        }
    }

    @Override
    public boolean retainAll(Collection<?> that) {
        lock.writeLock.lock();
        try {
            return inner.retainAll(that);
        } finally {
            lock.writeLock.unlock();
        }
    }

    @Override
    public int size() {
        lock.readLock.lock();
        try {
            return inner.size();
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public Object[] toArray() {
        lock.readLock.lock();
        try {
            return inner.toArray();
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public <T> T[] toArray(final T[] array) {
        lock.readLock.lock();
        try {
            return inner.toArray(array);
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public String toString() {
        lock.readLock.lock();
        try {
            return inner.toString();
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public FastSet<E>[] trySplit(int n) {
        lock.readLock.lock();
        try {
            return inner.clone().trySplit(n);
        } finally {
            lock.readLock.unlock();
        }
    }

    @Override
    public boolean until(Predicate<? super E> matching) {
        lock.readLock.lock();
        try {
            return inner.until(matching);
        } finally {
            lock.readLock.unlock();
        }
    }

}
