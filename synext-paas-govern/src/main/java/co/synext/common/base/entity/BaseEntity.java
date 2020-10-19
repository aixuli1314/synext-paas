package co.synext.common.base.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author xu.ran
 * @date 2020/1/30 0:15
 * @description: TODO
 */
@Getter
@Setter
public class BaseEntity {

    private String createBy;

    private String updateBy;

    private Date createTime;

    private Date updateTime;

}
