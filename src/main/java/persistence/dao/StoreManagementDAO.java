package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.StoreDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StoreManagementDAO
{
    private static SqlSessionFactory sqlSessionFactory = null;
    public StoreManagementDAO(SqlSessionFactory sqlSessionFactory)
    {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public static List<StoreDTO> showAll() {
        List<StoreDTO> dtos = new ArrayList<>();
        SqlSession session = sqlSessionFactory.openSession();
        try
        {
            dtos = session.selectList("mapper.StoreMapper.showAll");
            session.commit();
        }
        finally
        {
            session.rollback();
            session.close();
        }
        return dtos;
    }

    public List<StoreDTO> registerStore()
    {
        List<StoreDTO> dtos = new ArrayList<>();
        SqlSession session = sqlSessionFactory.openSession();
        try
        {
            dtos = session.selectList("mapper.StoreMapper.registerStore");
            session.commit();
        }
        finally
        {
            session.rollback();
            session.close();
        }
        return dtos;
    }

    public static List<StoreDTO> registerStore(Map<String, Object> params)
    {
        List<StoreDTO> dtos = null;
        SqlSession session = sqlSessionFactory.openSession();
        try
        {
            System.out.println("registStore");
            dtos = session.selectList("mapper.StoreMapper.registerStore",params);
        }
        finally
        {
            session.close();
        }
        return dtos;
    }

    public List<StoreDTO> selectStore(String str)
    {
        String storeName = str;
        List<StoreDTO> dtos = new ArrayList<>();
        SqlSession session = sqlSessionFactory.openSession();
        try
        {
            dtos = session.selectList("mapper.StoreMapper.selectStore", storeName);
            session.commit();
        }
        finally
        {
            session.rollback();
            session.close();
        }
        return dtos;
    }

}