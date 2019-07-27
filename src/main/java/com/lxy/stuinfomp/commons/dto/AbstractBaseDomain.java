package com.lxy.stuinfomp.commons.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AbstractBaseDomain implements Serializable {
    private Long id;
}
