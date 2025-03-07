package codegym.c10.com.service;

import codegym.c10.com.exception.NotFountException;

import java.util.Optional;

public interface IGenerateService<T> {
    Iterable<T> findAll();

    void save(T t);

    Optional<T> findById(Long id) throws NotFountException;

    void remove(Long id);
}
