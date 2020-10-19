package co.synext.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 专家信息表,是t_user表的扩展表,userType=3
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TUserExpert implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id主键,和t_user的ID保持一致
     */
    private String id;

    /**
     * URL路径中的部门类型,例如 URL路径中的 kjj 
     */
    @TableField("orgTypePathKey")
    private String orgTypePathKey;

    /**
     * 专家名称,和从t_user的name字段同步过来
     */
    private String name;
    /**
     * 专家电话,和从t_user的mobile字段同步过来
     */
    private String mobile;
    /**
     * 单位名称
     */
    @TableField("unitName")
    private String unitName;

    /**
     * 专家资格
     */
    @TableField(exist = false)
    private List<TUserExpertQualification> expertQualificationList;
    /**
     * 专家职业
     */
    @TableField(exist = false)
    private List<TUserExpertProfession> expertProfessionList;
    /**
     * 专家获奖
     */
    @TableField(exist = false)
    private List<TUserExpertAward> expertAwardList;
    /**
     * 注册状态,0资料保存未提交,1资料已提交,2内部审核中,3驳回,4申请成功
     */
    private Integer status;

    /**
     * 排序,查询时倒叙排列
     */
    private Integer sortno;

    /**
     * 是否有效(0否,1是)
     */
    private Integer active;


}
