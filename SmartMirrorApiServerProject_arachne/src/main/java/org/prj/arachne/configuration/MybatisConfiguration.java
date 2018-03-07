package org.prj.arachne.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.BooleanTypeHandler;
import org.apache.ibatis.type.DateTypeHandler;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(value="org.prj.arachne.infrastructure.mapper")
public class MybatisConfiguration {

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource)throws Exception{
		SqlSessionFactoryBean sessionFactory=new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		
		Resource[] res=new PathMatchingResourcePatternResolver()
							.getResources("classpath:/mappers/*_mapper.xml");
		
		//sessionFactory.setEnvironment("dev");
		
		sessionFactory.setMapperLocations(res);
		sessionFactory.setTypeAliasesPackage("org.prj.arachne.domain");
		sessionFactory.setTypeHandlers(new TypeHandler[] {
				new DateTypeHandler(),
				new BooleanTypeHandler()
		});
		
		
	
		return sessionFactory.getObject();
	}
	
	
	  /**
     * 마이바티스 {@link org.apache.ibatis.session.SqlSession} 빈을 등록한다.
     *
     * SqlSessionTemplate은 SqlSession을 구현하고 코드에서 SqlSession를 대체하는 역할을 한다.
     * 쓰레드에 안전하게 작성되었기 때문에 여러 DAO나 매퍼에서 공유 할 수 있다.
     */
    @Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    
    
    
    
    
	
}
