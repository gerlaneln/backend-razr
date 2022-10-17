package br.ufac.productmanager.service;

import java.util.List;

import br.ufac.productmanager.model.User;

public interface ICrudService<T> {

    public List<T> getAll();

    public T getById(Long id);

    public T save(T objeto, User user);

    public void delete(Long id);
    
}
