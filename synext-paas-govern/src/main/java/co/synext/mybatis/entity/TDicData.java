package co.synext.mybatis.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 公共的字典,通过pathkey区分不通的字典类型,例如 民族字典 pathkey 的值都是minzu, controller通过 /api/dicdata/{pathkey}/list 进行查看
 * </p>
 *
 * @author xu.ran
 * @since 2020-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TDicData implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 路径代码,用于标识字典路径,例如 pid是root,此菜单的comcode是  ,root,mydataid,
     */
    private String comcode;

    /**
     * 值
     */
    private String val;

    /**
     * 父级ID
     */
    private String pid;

    /**
     * 排序
     */
    private Integer sortno;

    /**
     * 描述
     */
    private String remark;

    /**
     * 是否有效(0否,1是)
     */
    private Integer active;

    /**
     * 类型
     */
    private String pathkey;


}
