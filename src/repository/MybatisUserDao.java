package repository;


import org.apache.ibatis.session.SqlSession;

import model.User;
import mybatis.AbstractRepository;

public class MybatisUserDao extends AbstractRepository{

	private final String namespace = "mybatis.UserMapper";
	private static MybatisUserDao instance = new MybatisUserDao();
	
	private MybatisUserDao() {	}
	
	public static MybatisUserDao getInstance() {
		return instance;
	}
	
	//회원가입
	public void joinUser(User user) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		String statement = null;
		try {
			statement = namespace + ".joinUser";
			sqlSession.insert(statement, user);
			sqlSession.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
	}

	
}
