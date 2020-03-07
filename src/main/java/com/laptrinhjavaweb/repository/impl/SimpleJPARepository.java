package com.laptrinhjavaweb.repository.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Table;
import com.laptrinhjavaweb.mapper.ResultSetMapper;
import com.laptrinhjavaweb.repository.EntityManagerFactory;
import com.laptrinhjavaweb.repository.JPARepository;

public class SimpleJPARepository<T> implements JPARepository<T> {

  private Class<T> clazz;

  @SuppressWarnings("unchecked")
  public SimpleJPARepository() {
    Type type = getClass().getGenericSuperclass();
    ParameterizedType parameterizedType = (ParameterizedType) type;
    clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
  }

  @Override
  public List<T> findAll(Map<String, Object> params, Object... where) {
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
    Connection connection = EntityManagerFactory.getConnection();
    String tableName = "";
    if (connection != null) {
      try {
        if (clazz.isAnnotationPresent(Table.class)) {
          Table table = clazz.getAnnotation(Table.class);
          tableName = table.name();
        }
        StringBuilder sql =
            new StringBuilder("select * from " + tableName + " tableName where 1 = 1");
        sql = createSQLfindAllCommon(params, sql);
        if (where != null && where.length == 1) {
          sql.append(where[0]);
        }
        statement = connection.prepareStatement(sql.toString());
        resultSet = statement.executeQuery();
        return resultSetMapper.mapRow(resultSet, this.clazz);
      } catch (SQLException e) {
        return null;
      } finally {
        try {
          if (connection != null) {
            connection.close();
          }
          if (statement != null) {
            statement.close();
          }
          if (resultSet != null) {
            resultSet.close();
          }
        } catch (Exception e2) {
          return null;
        }
      }
    }
    return new ArrayList<>();
  }

  protected StringBuilder createSQLfindAllCommon(Map<String, Object> params, StringBuilder sql) {
    if (params != null && params.size() > 0) {
      String[] keys = new String[params.size()];
      Object[] values = new Object[params.size()];
      int i = 0;
      for (Map.Entry<String, Object> item : params.entrySet()) {
        keys[i] = item.getKey();
        values[i] = item.getValue();
        i++;
      }
      for (int index = 0; index < keys.length; index++) {
        if (StringUtils.isNotBlank(values[index].toString())) {
          if (values[index] instanceof String) {
            sql.append(" and b." + keys[index] + " like '%"+values[index]+"%'");
          } else {
            if (values[index] != null) {
              sql.append(" and b." + keys[index] + " = " + values[index] + "");
            }
          }
        } 
      }
    }
    return sql;
  }

  @Override
  public void insert(String sql, Object... objects) {
    Connection connection = null;
    PreparedStatement statement = null;
    try {
      connection = EntityManagerFactory.getConnection();
      connection.setAutoCommit(false);
      statement = connection.prepareStatement(sql);
      int index = 1;
      for (Object object : objects) {
        statement.setObject(index, object);
        index++;
      }
      statement.executeUpdate();
      connection.commit();
    } catch (SQLException e) {
      if (connection != null) {
        try {
          connection.rollback();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      }
    } finally {
      try {
        if (connection != null) {
          connection.close();
        }
        if (statement != null) {
          statement.close();
        }
      } catch (SQLException e2) {
        e2.printStackTrace();
      }
    }
  }
  
  

  @Override
  public Long insert(Object objects) {
    String sql = createSQLInsert();
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    try {
      connection = EntityManagerFactory.getConnection();
      connection.setAutoCommit(false);
      statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      Class<?> zclass = objects.getClass();
      int index = 1;
      for (Field zfield : zclass.getDeclaredFields()) {
        zfield.setAccessible(true);
        statement.setObject(index, zfield.get(objects));
        index++;
      }
      Class<?> parentClass = clazz.getSuperclass();
      int indexParent = zclass.getDeclaredFields().length + 1;
      while(parentClass != null) {
        for (Field zfield : parentClass.getDeclaredFields()) {
          zfield.setAccessible(true);
          statement.setObject(indexParent, zfield.get(objects));
          indexParent++;
        }
        parentClass = parentClass.getSuperclass();
      }
      int result = statement.executeUpdate();
      resultSet = statement.getGeneratedKeys();
      connection.commit();
      if(result > 0) {
         while(resultSet.next()) {
           Long id = resultSet.getLong(1);
           return id;
         }
      }
    } catch (SQLException e) {
      if (connection != null) {
        try {
          connection.rollback();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      }
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (IllegalAccessException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    } finally {
      try {
        if (connection != null) {
          connection.close();
        }
        if (statement != null) {
          statement.close();
        }
        if(resultSet != null) {
          resultSet.close();
        }
      } catch (SQLException e2) {
        e2.printStackTrace();
      }
    }
    return -1L;
  }
  
  private String createSQLInsert() {
    String tableName = "";
    if (clazz.isAnnotationPresent(Table.class)) {
      Table table = clazz.getAnnotation(Table.class);
      tableName = table.name();
    }
    StringBuilder fields = new StringBuilder("");
    StringBuilder params = new StringBuilder("");
    for (Field field : clazz.getDeclaredFields()) {
      if (fields.length() > 1) {
        fields.append(",");
        params.append(",");
      }
      if(field.isAnnotationPresent(Column.class)) {
        Column colum = field.getAnnotation(Column.class);
        fields.append(colum.name());
        params.append("?"); 
      }
    }
    Class<?> parentClass = clazz.getSuperclass();
    while(parentClass != null) {
      for (Field field : parentClass.getDeclaredFields()) {
        if (fields.length() > 1) {
          fields.append(",");
          params.append(",");
        }
        if(field.isAnnotationPresent(Column.class)) {
          Column colum = field.getAnnotation(Column.class);
          fields.append(colum.name());
          params.append("?"); 
        }
      }
      parentClass = parentClass.getSuperclass();
    }
    String sql = "INSERT INTO "+tableName+"("+fields.toString()+") VALUES("+params.toString()+")";
    return sql;
  }
  
  @Override
  public void update(long id, Object objects) {
    String sql = createSQLUpdate(id);
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    try {
      connection = EntityManagerFactory.getConnection();
      connection.setAutoCommit(false);
      statement = connection.prepareStatement(sql);
      Class<?> zclass = objects.getClass();
      int index = 1;
      for (Field zfield : zclass.getDeclaredFields()) {
        zfield.setAccessible(true);
        statement.setObject(index, zfield.get(objects));
        index++;
      }
      Class<?> parentClass = clazz.getSuperclass();
      int indexParent = zclass.getDeclaredFields().length + 1;
      while(parentClass != null) {
        for (Field zfield : parentClass.getDeclaredFields()) {
          zfield.setAccessible(true);
          statement.setObject(indexParent, zfield.get(objects));
          indexParent++;
        }
        parentClass = parentClass.getSuperclass();
      }
      statement.executeUpdate();
      connection.commit();
    } catch (SQLException e) {
      if (connection != null) {
        try {
          connection.rollback();
          System.out.println(e.getMessage());
          System.out.println(e.getCause());
          System.out.println(e.getCause());
        } catch (SQLException e1) {
          System.out.println(e1.getMessage());
        }
      }
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (IllegalAccessException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    } finally {
      try {
        if (connection != null) {
          connection.close();
        }
        if (statement != null) {
          statement.close();
        }
        if(resultSet != null) {
          resultSet.close();
        }
      } catch (SQLException e2) {
        e2.printStackTrace();
      }
    }
  }

  private String createSQLUpdate(long id) {
    String tableName = "";
    if (clazz.isAnnotationPresent(Table.class)) {
      Table table = clazz.getAnnotation(Table.class);
      tableName = table.name();
    }
    StringBuilder fields = new StringBuilder("");
    StringBuilder params = new StringBuilder("=?");
    for (Field field : clazz.getDeclaredFields()) {
      if (fields.length() > 1) {
        fields.append(",");
      }
      if(field.isAnnotationPresent(Column.class)) {
        Column colum = field.getAnnotation(Column.class);
        fields.append(colum.name() + params); 
      }
    }
    Class<?> parentClass = clazz.getSuperclass();
    while(parentClass != null) {
      for (Field field : parentClass.getDeclaredFields()) {
        if (fields.length() > 1) {
          fields.append(",");
        }
        if(field.isAnnotationPresent(Column.class)) {
          Column colum = field.getAnnotation(Column.class);
          fields.append(colum.name() + params); 
        }
      }
      parentClass = parentClass.getSuperclass();
    }
    String sql = "update "+tableName+" set "+fields.toString()+" where id = "+id+"";
    return sql;
  }

  

  @Override
  public List<T> findAll(String sql, Object... where) {
    Statement statement = null;
    ResultSet resultSet = null;
    ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
    Connection connection = EntityManagerFactory.getConnection();
    if (connection != null) {
      try {
        StringBuilder buider = new StringBuilder(sql);
        if (where != null && where.length == 1) {
          buider.append(where[0]);
        }
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        return resultSetMapper.mapRow(resultSet, this.clazz);
      } catch (SQLException e) {
        return null;
      } finally {
        try {
          if (connection != null) {
            connection.close();
          }
          if (statement != null) {
            statement.close();
          }
          if (resultSet != null) {
            resultSet.close();
          }
        } catch (Exception e2) {
          return null;
        }
      }
    }
    return new ArrayList<>();
  }

  @Override
  public void update(String sql, Object... objects) {
    Connection connection = null;
    PreparedStatement statement = null;
    try {
      connection = EntityManagerFactory.getConnection();
      connection.setAutoCommit(false);
      statement = connection.prepareStatement(sql);
      int index = 1;
      for (Object object : objects) {
        statement.setObject(index, object);
        index++;
      }
      statement.executeUpdate();
      connection.commit();
    } catch (SQLException e) {
      if (connection != null) {
        try {
          connection.rollback();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      }
    } finally {
      try {
        if (connection != null) {
          connection.close();
        }
        if (statement != null) {
          statement.close();
        }
      } catch (SQLException e2) {
        e2.printStackTrace();
      }
    }
  }
}
