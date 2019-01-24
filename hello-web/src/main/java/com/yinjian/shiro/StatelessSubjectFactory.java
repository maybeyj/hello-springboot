package com.yinjian.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.springframework.stereotype.Component;

/**
 * @author: Yin Jian
 * @create: 2019-01-23 09:43
 */
public class StatelessSubjectFactory extends DefaultWebSubjectFactory {
    @Override
    public Subject createSubject(SubjectContext context) {
        //不生产session
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }
}
