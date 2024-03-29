package com.lxy.stuinfomp.commons.dto;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
public class SuccessResult<T extends AbstractBaseDomain> extends AbstractBaseResult{
    private Links links;
    private List<DataBean> data;
    private List<T> rows;

    public SuccessResult (String self,T attributes){
        links = new Links();
        links.setSelf(self);
        createDataBean(null,attributes);

    }

    public SuccessResult(String self,int next,int last,List<T> attributes){
        links = new Links();
        links.setSelf(self);
        links.setNext(self + "?page" + next);
        links.setLast(self + "?page" + last);
        this.rows = attributes;
        attributes.forEach(attribute->createDataBean(self,attribute));
    }


    private void createDataBean(String self,T attributes){
        if (data == null){
            data = Lists.newArrayList();
        }
        DataBean dataBean = new DataBean();
        dataBean.setId(attributes.getId());
        dataBean.setType(attributes.getClass().getSimpleName());
        dataBean.setAttributes(attributes);
        if (StringUtils.isNoneBlank(self)){
            Links links = new Links();
            links.setSelf(self + "/" +attributes.getId());
            dataBean.setLinks(links);
        }

        data.add(dataBean);
    }
}
