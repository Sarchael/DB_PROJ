package com.project.bootfx.app.dao;

import java.util.List;

public interface IDataAccessObject {

    public <T> T save(T c);
    public <T> T getById(Class<T> c, int id);
    public <T> List<T> readAll(Class<T> c);
    public <T> boolean deletebyID(Class <T>c, int id);
}
