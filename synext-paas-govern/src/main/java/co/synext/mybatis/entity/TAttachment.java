package co.synext.mybatis.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 专家专业 信息表
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TAttachment implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id主键
     */
    private String id;

    /**
     * URL路径中的部门类型,例如 URL路径中的 kjj 
     */
    @TableField("orgTypePathKey")
    private String orgTypePathKey;

    /**
     * 业务ID,用于业务关联查询
     */
    @TableField("businessId")
    private String businessId;

    /**
     * 业务模块名称
     */
    @TableField("businessModule")
    private String businessModule;

    /**
     * 附件名称
     */
    @TableField("fileName")
    private String fileName;

    /**
     * 路径
     */
    @TableField("fileURL")
    private String fileURL;

    /**
     * 文件后缀
     */
    private String suffix;

    /**
     * 文件大小,单位K
     */
    @TableField("fileSize")
    private Integer fileSize;

    /**
     * 专业开始时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    /**
     * 最后下载时间
     */
    @TableField("lastDownTime")
    private LocalDateTime lastDownTime;

    /**
     * 排序,查询时倒叙排列
     */
    private Integer sortno;

    /**
     * 是否有效(0否,1是)
     */
    private Integer active;


}
