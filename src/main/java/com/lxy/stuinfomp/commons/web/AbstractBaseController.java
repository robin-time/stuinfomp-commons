package com.lxy.stuinfomp.commons.web;

import com.lxy.stuinfomp.commons.dto.AbstractBaseDomain;
import com.lxy.stuinfomp.commons.dto.AbstractBaseResult;
import com.lxy.stuinfomp.commons.dto.BaseResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author lxy
 */
public abstract class AbstractBaseController<T extends AbstractBaseDomain> {

    private static final String LOGGING_LEVEL_MY_SHOP = "logging.level.com.lxy.stuinfomp";

    protected HttpServletRequest request;
    protected HttpServletResponse response;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @ModelAttribute
    public void initReqAndRes (HttpServletRequest request,HttpServletResponse response){
        this.request  = request;
        this.response = response;
    }

    protected AbstractBaseResult success(String self,T attribute){
        return BaseResultFactory.getInstance(response).build(self,attribute);
    }

    protected AbstractBaseResult success(String self, int next, int last, List<T> attributes){
        return BaseResultFactory.getInstance(response).build(self,next,last,attributes);
    }

    protected AbstractBaseResult error(int code,String title,String detail){
        return BaseResultFactory.getInstance(response).build(code,title,detail,applicationContext.getEnvironment().getProperty(LOGGING_LEVEL_MY_SHOP));
    }

    protected AbstractBaseResult error(String title,String detail){
        return error(HttpStatus.UNAUTHORIZED.value(),title,detail);
    }
}
