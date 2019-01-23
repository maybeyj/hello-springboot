package com.yinjian.shiro;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
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
     * subject工厂
     * @return
     */
   /* @Bean
    public StatelessSubjectFactory subjectFactory(){
        StatelessSubjectFactory statelessSubjectFactory = new StatelessSubjectFactory();
        return statelessSubjectFactory;
    }*/

    /**
     * session会话管理器，禁用掉会话调度器
     * @return
     */
    /*@Bean
    public SessionManager sessionManager(){
        DefaultSessionManager defaultSessionManager = new DefaultSessionManager();
        defaultSessionManager.setSessionValidationSchedulerEnabled(false);
        return defaultSessionManager;
    }*/

    /**
     *  security管理器
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        //defaultSecurityManager.setSubjectFactory(subjectFactory);
        //defaultSecurityManager.setSessionManager(sessionManager);
        defaultSecurityManager.setRealm(statelessRealm);
        return defaultSecurityManager;
    }

    private StatelessAuthenticationFilter statelessAuthenticationFilter =new StatelessAuthenticationFilter();

    /**
     * 过滤器
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //自定义配置过滤器
        Map<String, Filter> filterMap=new HashMap<>();
        filterMap.put("anon",new AnonymousFilter());
        filterMap.put("statelessAuthenticationFilter", statelessAuthenticationFilter);
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
        //配置退出过滤器
        filterChainDefinitionMap.put("/logout", "logout");
        //set
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
}
