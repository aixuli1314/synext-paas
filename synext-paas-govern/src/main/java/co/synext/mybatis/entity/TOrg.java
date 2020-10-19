package co.synext.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 部门
 * </p>
 *
 * @author xu.ran
 * @since 2020-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TOrg implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 名称
     */
    private String name;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 0科技局,1注册企业,9虚拟部门
     */
    private Integer orgType;
    /**
     * 排序,查询时倒叙排列
     */
    private Integer sortno;
    /**
     * 路径代码,用于标识部门路径,例如 pid是root,此部门的comcode是  ,root,myorgid,
     */
    private String comcode;
    /**
     * URL路径中的部门类型,例如 URL路径中的 kjj
     */
    private String orgTypePathKey;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 上级部门ID
     */
    private String pid;
    /**
     * 是否有效(0否,1是)
     */
    private Integer active;

    /**
     * 更新者ID
     */
    private String updateUserId;
    /**
     * 创建者ID
     */
    private String createUserId;

}
