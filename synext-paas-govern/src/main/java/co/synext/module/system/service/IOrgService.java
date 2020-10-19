package co.synext.module.system.service;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.mybatis.entity.TOrg;
import co.synext.module.system.dto.OrgInputDTO;
import co.synext.module.system.dto.OrgUpdateDTO;
import co.synext.module.system.dto.OrgQueryDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 * 部门 服务类
 * </p>
 *
 * @author xu.ran
 * @since 2020-08-27
 */
public interface IOrgService extends IService<TOrg> {

    /**
     * 分页方法
     * @param orgQueryDTO
     * @return
     */
    ReturnDatas page(OrgQueryDTO orgQueryDTO);

    ReturnDatas getAllOrg();

    /**
     * 保存方法
     * @param orgInputDTO
     * @return
     */
    ReturnDatas save(OrgInputDTO orgInputDTO);

    /**
     * 更新方法
     * @param orgUpdateDTO
     * @return
     */
    ReturnDatas update(OrgUpdateDTO orgUpdateDTO);

    /**
     * 查询方法
     * @param id
     * @return
     */
    ReturnDatas findById(Serializable id);

    /**
     * 获取结构树
     * @return
     */
    ReturnDatas getOrgTree();

    /**
     * 删除多个方法
     * @param ids
     * @return
     */
    ReturnDatas batchDelete(Collection<String> ids);

/*    *//**
     * 删除方法
     * @param id
     * @return
     *//*
    ReturnDatas deleteById(Serializable id);*/
}
