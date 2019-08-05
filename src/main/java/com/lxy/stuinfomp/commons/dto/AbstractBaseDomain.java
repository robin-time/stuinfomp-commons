package com.lxy.stuinfomp.commons.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
public abstract class AbstractBaseDomain implements Serializable {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String creator;

    @Column(name = "gmt_created")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date gmtCreated;

    private String modifier;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "gmt_modified")
    private Date gmtModified;

    /**
     * 0代表未删除，1代表逻辑删除
     */
    @Column(name = "is_delete")
    private Integer isDelete = 0;
}
