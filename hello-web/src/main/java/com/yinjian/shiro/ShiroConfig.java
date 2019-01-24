package com.yinjian.shiro;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: Yin Jian
 * @create: 2019-01-22 09:33
 */
@Configuration
public class ShiroConfig {
    private StatelessRealm statelessRealm;

    public ShiroConfig(StatelessRealm statelessRealm) {
        this.statelessRealm = statelessRealm;
    }

    /**
     * subject工厂(已经关闭session)
     *
     * @return
     */
    /*@Bean
    public StatelessSubjectFactory subjectFactory() {
        StatelessSubjectFactory statelessSubjectFactory = new StatelessSubjectFactory();
        return statelessSubjectFactory;
    }*/

    /**
     * 关闭session
     * @return
     */
    @Bean
    public SessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        return sessionManager;
    }

    /**
     * security管理器
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager(SessionManager sessionManager) {
        DefaultSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        //defaultSecurityManager.setSubjectFactory(subjectFactory);
        defaultSecurityManager.setSessionManager(sessionManager);
        defaultSecurityManager.setRealm(statelessRealm);
        /*
         * 关闭shiro自带的session，详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        defaultSecurityManager.setSubjectDAO(subjectDAO);
        return defaultSecurityManager;
    }


    /**
     * 过滤器
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //自定义配置过滤器
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("anon", new AnonymousFilter());
        filterMap.put("statelessAuthenticationFilter", new StatelessAuthenticationFilter());
        //set
        shiroFilterFactoryBean.setFilters(filterMap);

        //自定义拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // swagger不进行拦截
        filterChainDefinitionMap.put("/swagger-ui.html/**", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        // 功倍电商引擎系统拦截器
        filterChainDefinitionMap.put("/**", "statelessAuthenticationFilter");
        //set
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
}
