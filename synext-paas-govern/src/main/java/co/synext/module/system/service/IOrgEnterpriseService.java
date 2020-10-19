package co.synext.module.system.service;

import co.synext.common.base.resp.ReturnDatas;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.mybatis.entity.TOrgEnterprise;
import co.synext.module.system.dto.OrgEnterpriseInputDTO;
import co.synext.module.system.dto.OrgEnterpriseUpdateDTO;
import co.synext.module.system.dto.OrgEnterpriseQueryDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 *  企业信息表,企业实际是t_org部门,orgType=1.给部门创建一个账号,作为主管,用于登录.附件关联附件表t_attachment 服务类
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-10
 */
public interface IOrgEnterpriseService extends IService<TOrgEnterprise> {

    /**
     * 分页方法
     * @param orgEnterpriseQueryDTO
     * @return
     */
    ReturnDatas page(OrgEnterpriseQueryDTO orgEnterpriseQueryDTO);

    /**
     * 保存方法
     * @param orgEnterpriseInputDTO
     * @return
     */
    ReturnDatas save(OrgEnterpriseInputDTO orgEnterpriseInputDTO);

    /**
     * 更新方法
     * @param orgEnterpriseUpdateDTO
     * @return
     */
    ReturnDatas update(OrgEnterpriseUpdateDTO orgEnterpriseUpdateDTO);

    /**
     * 查询方法
     * @param id
     * @return
     */
    ReturnDatas findById(Serializable id);

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
