package com.lxy.stuinfomp.commons.dto;

import java.util.List;

public class BaseResultFactory<T extends AbstractBaseDomain> {

    private static final String LOGGER_LEVEL_DEBUG = "DEBUG";

    private BaseResultFactory(){}

    private static BaseResultFactory baseResultFactory;

    /**
     * 单例设计模式
     * @return
     */
    public static BaseResultFactory getInstance(){
        if (baseResultFactory == null){
            synchronized (BaseResultFactory.class){
                if (baseResultFactory == null){
                    baseResultFactory = new BaseResultFactory();
                }
            }
        }
        return baseResultFactory;
    }

    /**
     * 单个结果的成功返回结果
     * @param self
     * @param attributes
     * @return
     */
    public AbstractBaseResult build(String self,T attributes){
        return new SuccessResult(self,attributes);
    }

    /**
     * 多条结果成功的返回结果
     * @param self
     * @param next
     * @param last
     * @param attributes
     * @return
     */
    public AbstractBaseResult build(String self, int next, int last, List<T> attributes){
        return new SuccessResult(self,next,last,attributes);
    }

    /**
     *  错误结果返回
     * @param code
     * @param title
     * @param detail
     * @param level 日志级别，如果是debug模式，就显示detail详情，否则是null
     * @return
     */
    public static AbstractBaseResult build(int code,String title,String detail,String level){
       if(LOGGER_LEVEL_DEBUG.equals(level.toUpperCase())){
           return new ErrorResult(code,title,detail);
       }else {
           return new ErrorResult(code,title,null);
       }
    }
}
