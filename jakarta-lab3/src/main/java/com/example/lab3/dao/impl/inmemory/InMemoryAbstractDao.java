package com.example.lab3.dao.impl.inmemory;

import com.example.lab3.dao.AbstractDao;

import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class InMemoryAbstractDao<T> implements AbstractDao<T> {
    protected Map<Integer, T> entities;
    protected Function<T, Integer> getId;
    protected BiConsumer<T, Integer> setId;
    protected InMemoryDb db;

    InMemoryAbstractDao(InMemoryDb db, Map<Integer, T> entities, Function<T, Integer> getId, BiConsumer<T, Integer> setId) {
        this.entities = entities;
        this.getId = getId;
        this.setId = setId;
        this.db = db;
    }

    @Override
    public void delete(T entity) {
        entities.remove(getId.apply(entity));
    }

    @Override
    public T get(Integer id) {
        return entities.get(id);
    }

    @Override
    public Collection<T> getAll() {
        return entities.values();
    }

    @Override
    public void create(T entity) {
        int maxId = entities.keySet().stream().mapToInt(Integer::intValue).max().orElse(0);
        setId.accept(entity, ++maxId);
        entities.put(maxId, entity);
    }

    @Override
    public void update(T entity) {
        entities.put(getId.apply(entity), entity);
    }
}
