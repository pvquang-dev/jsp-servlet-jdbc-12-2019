package com.laptrinhjavaweb.repository;

import java.util.List;
import java.util.Map;

public interface JPARepository<T> {
  List<T> findAll(Map<String, Object> params, Object... where);

  List<T> findAll(String sql, Object... where);

  void insert(String sql, Object... objects);

  Long insert(Object objects);

  void update(long id, Object objects);

  void update(String sql, Object... objects);
}
