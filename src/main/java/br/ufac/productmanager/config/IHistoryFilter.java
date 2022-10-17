package br.ufac.productmanager.config;

import java.util.List;

public interface IHistoryFilter<E> {

    public List<E> getHistoryByDay();

    public List<E> getHistoryByMonth();

    public List<E> getHistoryByUser();

    public List<E> getHistoryByGeneralModification();

    public List<E> getHistoryByGenericModification();

    public List<E> getHistoryByRegion();
}