package com.example.demo.repository;

import com.example.demo.domainobject.CustomerDO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class CustomerRepository implements ICustomerRepository{
    @Override
    public Optional<CustomerDO> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public List<CustomerDO> findAll() {
        return null;
    }

    @Override
    public List<CustomerDO> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<CustomerDO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<CustomerDO> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(CustomerDO customerDO) {

    }

    @Override
    public void deleteAll(Iterable<? extends CustomerDO> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends CustomerDO> S save(S s) {
        return null;
    }

    @Override
    public <S extends CustomerDO> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<CustomerDO> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends CustomerDO> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<CustomerDO> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public CustomerDO getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends CustomerDO> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CustomerDO> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends CustomerDO> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends CustomerDO> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CustomerDO> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CustomerDO> boolean exists(Example<S> example) {
        return false;
    }
}
