package co.synext.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典类别管理,对应t_dic_data表的pathkey
 * </p>
 *
 * @author xu.ran
 * @since 2020-08-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TDicDataType implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    /**
     * URL路径中的部门类型,例如 URL路径中的 kjj 
     */
    @TableField("orgTypePathKey")
    private String orgTypePathKey;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     */
    private String pathkey;

    /**
     * 是否有效(0否,1是)
     */
    private Integer active;


}
