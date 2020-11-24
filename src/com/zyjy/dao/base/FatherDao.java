package com.zyjy.dao.base;

import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName FatherDao
 * @Description dao的增删改查
 * 使用要求：数据库的字段名要规范
 * *                 因为该类在查询赋值时，需要将驼峰命名转换为下划线命名，才能获得值
 * @Author 清Great
 * @Date 2020/10/13 11:02
 */
public abstract class FatherDao<T> extends BaseDao {
    private Class<T> clazz;

    {
        Type genericSuperclass = this.getClass().getGenericSuperclass();

        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;

        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

        clazz = (Class<T>) actualTypeArguments[0];
    }


    public List<T> find() throws Exception {
        String sql = "select * from " + getTableName();
        return commonQuery(getConnection(), sql);
    }


    public int count() throws SQLException {
        String sql = "select count(*) from " + getTableName();
        return countTotalRecord(getConnection(), sql);
    }

    /**
     * 通用添加
     * id要自增
     *
     * @return
     */
    public int add(T t) throws SQLException {
        String sql = "insert into " + getTableName() + "(" + getTableColumnName() + ") values(" + getTableWhat() + ")";
        return commonUpdate(getConnection(), sql, getObjectData(t));
    }


    public int delete(int id) throws SQLException {
        String sql = "delete from " + getTableName() + " where " + clazz.getSimpleName().toLowerCase() + "_id = ?";
        return commonUpdate(getConnection(), sql, id);
    }


    public int update(T t) throws Exception {
        String sql = "update " + getTableName() + " set (" + getTableColumnName() + ") = (" + getTableWhat() + ") " +
                "where " + clazz.getSimpleName().toLowerCase() + "_id = ?";
        return commonUpdate(getConnection(), sql, getObjectAllData(t));
    }


    /**
     * 获取表的名称
     *
     * @return
     */
    public String getTableName() {
        String simpleName = clazz.getSimpleName();
        String s = camelsToSqlCase(simpleName);
        return getTableFirstName() + s;
    }


    /**
     * 只获取当前类的属性
     */
    public String getTableColumnName() {
        Field[] declaredFields = clazz.getDeclaredFields();
        StringBuffer sb = new StringBuffer();
        String simpleName = clazz.getSimpleName().toLowerCase();
        for (Field declaredField : declaredFields) {
            if (declaredField.getName().toLowerCase().equals(simpleName + "id")) {
                continue;
            }
            sb.append(camelsToSqlCase(declaredField.getName())).append(",");
        }
        String value = String.valueOf(sb);
        return value.substring(0, value.length() - 1);
    }

    /**
     * 拼接问号字符串
     */
    public String getTableWhat() {
        Field[] declaredFields = clazz.getDeclaredFields();
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < declaredFields.length; i++) {
            sb.append("?").append(",");
        }
        String value = String.valueOf(sb);
        return value.substring(0, value.length() - 1);
    }


    /**
     * @param t
     * @return 获取该类除了主键的所有属性
     */
    public Object[] getObjectData(T t) {
        Field[] declaredFields = clazz.getDeclaredFields();
        String simpleName = clazz.getSimpleName().toLowerCase();
        Object[] data = new Object[declaredFields.length - 1];
        try {
            for (int i = 0; i < declaredFields.length; i++) {
                Field declaredField = declaredFields[i];
                Method method = clazz.getDeclaredMethod(creatGetter(declaredField.getName()));
                if (!declaredField.getName().toLowerCase().equals(simpleName + "id")) {
                    data[i - 1] = method.invoke(t);
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return data;
    }


    /**
     * @param t
     * @return 获取该类所有属性, 并讲主键属性放在最后一个，用于更新使用
     */
    public Object[] getObjectAllData(T t) throws Exception {
        Field[] declaredFields = clazz.getDeclaredFields();
        String simpleName = clazz.getSimpleName().toLowerCase();
        Object[] data = new Object[declaredFields.length];

        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            Method method = clazz.getDeclaredMethod(creatGetter(declaredField.getName()));
            if (!declaredField.getName().toLowerCase().equals(simpleName + "id")) {
                data[i - 1] = method.invoke(t);
            } else {
                data[declaredFields.length - 1] = method.invoke(t);
            }
        }

        return data;
    }


    /**
     * @param str 要转换的字符串
     * @return 返回转换为符合数据库命名的下划线的字符串
     */
    public static String camelsToSqlCase(String str) {

        StringBuffer sb = new StringBuffer();
        //获取所有的字段
        char[] chars = str.toCharArray();
        //判断哪些是大写小写
        for (char aChar : chars) {
            if (aChar >= 'A' && aChar <= 'Z') {
                sb.append('_');
                sb.append(aChar);
            } else {
                sb.append(aChar);
            }
        }
        return String.valueOf(sb).toUpperCase();
    }


    /**
     * 获取该类所有属性，包括父类
     */
    private List<Field> getAllDeclaredField(Class<?> clazz, List<Field> fieldList) {
        Field[] fields = clazz.getDeclaredFields();
        fieldList.addAll(Arrays.asList(fields));
        //递归出口
        if (fields.length == 0) return fieldList;
        Class<?> superclass = clazz.getSuperclass();
        getAllDeclaredField(superclass, fieldList);
        return fieldList;
    }


    public static String creatGetter(String coluName) {
        String getter = null;
        getter = "get" + coluName.substring(0, 1).toUpperCase() + coluName.substring(1);
        return getter;
    }


    /**
     * 查询通用（多个结果）
     *
     * @param sql  sql语句
     * @param args 填充预编译参数
     * @return 返回一个集合
     */
    public ArrayList<T> commonQuery(Connection connection, String sql, Object... args) throws Exception {
        ArrayList<T> ts;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ts = new ArrayList<T>();
            ps = null;
            rs = null;

            ps = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {

                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();

//            ResultSetMetaData rsmd = rs.getMetaData();//结果集元数据
            //获取所有的属性（包括父类）
            List<Field> fieldList = new ArrayList<>();
            List<Field> allDeclaredField = getAllDeclaredField(clazz, fieldList);
            while (rs.next()) {
                T t = getBean(allDeclaredField, rs);
                ts.add(t);
            }
        } finally {

            closeResource(rs, ps);
        }


        return ts;
    }

    /**
     * 查询通用（单个结果）  一般用在账号密码查询
     *
     * @param sql  sql语句
     * @param args 填充预编译参数
     * @return 返回一个Bean
     */
    public T findSingleBean(Connection connection, String sql, Object... args) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        T t;
        try {

            ps = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();

            //获取所有的属性（包括父类）
            List<Field> fieldList = new ArrayList<>();
            List<Field> allDeclaredField = getAllDeclaredField(clazz, fieldList);
            t = null;
            if (rs.next()) {
                t = getBean(allDeclaredField, rs);
            }
        } finally {
            closeResource(rs, ps);
        }
        return t;
    }

    public T getBean(List<Field> allDeclaredField, ResultSet rs) throws Exception {

        String fieldName = null;

        Object columnValue;
        T t = clazz.newInstance();

        for (Field field : allDeclaredField) {
            field.setAccessible(true);
            Class<?> type = field.getType();
            fieldName = camelsToSqlCase(field.getName());

            //由于oracle版本的数值反射得到的都是number类型所以需要进行一个转换

            if (type.getName().equals("int")
                    || type.getName().equals("java.lang.Integer")) {
//                System.out.println(fieldName);
                columnValue = rs.getInt(fieldName);
            } else if (type.getName().equals("long")
                    || type.getName().equals("java.lang.Long")) {
                columnValue = rs.getLong(fieldName);
            } else if (type.getName().equals("float")
                    || type.getName().equals("java.lang.Float")) {
                columnValue = rs.getFloat(fieldName);
            } else if (type.getName().equals("double")
                    || type.getName().equals("java.lang.Double")) {
                columnValue = rs.getDouble(fieldName);
            } else if (type.getName().equals("java.util.Date")) {
                columnValue = rs.getTimestamp(fieldName);
            } else if (type.getName().equals("java.sql.Date")) {
                columnValue = rs.getDate(fieldName);
            } else if (type.getName().equals("java.sql.TimeStamp")) {
                columnValue = rs.getTimestamp(fieldName);
            } else {
//                System.out.println(fieldName);
                columnValue = rs.getObject(fieldName);
            }
            field.set(t, columnValue);
        }
        return t;

    }


    /**
     * 通用增删改
     *
     * @param sql
     * @param args
     * @return
     */
    public int commonUpdate(Connection connection, String sql, Object... args) throws SQLException {
        PreparedStatement ps = null;
        int update;
        try {
            ps = connection.prepareStatement(sql);
//            System.out.println(sql);
            for (int i = 0; i < args.length; i++) {
                //                System.out.println(args[i]);
                ps.setObject(i + 1, args[i]);
            }
            update = ps.executeUpdate();

            if (update <= 0) {
                //            System.out.println("更新数量" + update);

                throw new SQLException();
            }
        } finally {
            closeResource(null, ps);
        }
        return update;
    }


    /**
     * 查询总记录条数
     */
    public int countTotalRecord(Connection connection, String sql, Object... args) throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;

        int count;
        try {
            ps = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();

            count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } finally {

            closeResource(rs, ps);
        }


        return count;
    }


    /**
     * 适用oracle的插入并返回id
     * begin sql语句 ruturning id into ?;end;
     */
    public int insertReturnId(Connection connection, String sql, Object... args) throws SQLException {
        CallableStatement pc = null;

        int id;
        try {
            pc = connection.prepareCall(sql);
            int i;
            for (i = 0; i < args.length; i++) {
                pc.setObject(i + 1, args[i]);
            }
            pc.registerOutParameter(i + 1, Types.NUMERIC);
            int insert = pc.executeUpdate();

            id = -1;
            if (insert > 0) {
                id = pc.getInt(i + 1);
            }
        } finally {

            closeResource(null, pc);
        }
        return id;
    }


    public int findNextVal(Connection connection, String seq) throws SQLException {
        String sql = "select " + seq + ".Nextval from DUAL";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResource(rs, ps);
        }
        return 0;
    }

}
