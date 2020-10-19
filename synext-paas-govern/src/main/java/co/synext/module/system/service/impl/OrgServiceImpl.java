package co.synext.module.system.service.impl;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.common.base.vo.UserVo;
import co.synext.module.system.service.IOrgRoleService;
import co.synext.module.system.service.IUserOrgService;
import co.synext.module.system.service.IUserService;
import co.synext.module.system.vo.MenuVo;
import co.synext.module.system.vo.OrgVO;
import co.synext.mybatis.entity.TMenu;
import co.synext.mybatis.entity.TOrg;
import co.synext.mybatis.entity.TUserOrg;
import co.synext.mybatis.mapper.TOrgMapper;
import co.synext.module.system.service.IOrgService;
import co.synext.module.system.dto.OrgInputDTO;
import co.synext.module.system.dto.OrgUpdateDTO;
import co.synext.module.system.dto.OrgQueryDTO;
import co.synext.common.base.service.BaseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author xu.ran
 * @since 2020-08-27
 */
@Service("OrgServiceImpl")
public class OrgServiceImpl extends BaseService<TOrgMapper, TOrg> implements IOrgService {
    @Autowired
    private IOrgRoleService orgRoleService;
    @Autowired
    private IUserOrgService userOrgService;
    @Autowired
    private IUserService userService;

    @Override
    public ReturnDatas page(OrgQueryDTO orgQueryDTO) {
        TOrg org = orgQueryDTO.convertToEntity();
        return ReturnDatas.ok(baseMapper.selectPage(orgQueryDTO.page(), getLambdaQueryWrapper().setEntity(org)));
    }

    @Override
    public ReturnDatas getAllOrg() {
        QueryWrapper queryWrapper = Wrappers.query();
        ArrayList<TOrg> orgs = (ArrayList<TOrg>) baseMapper.selectList(queryWrapper);
        ArrayList<OrgVO> orgVOS = new ArrayList<>();
        for (TOrg org : orgs) {
            OrgVO orgVo = copy2(org, OrgVO.class);
            List<TUserOrg> tUserOrgs = userOrgService.findByOrgId(org.getId());
            ArrayList<UserVo> users = new ArrayList<>();
            for (TUserOrg tUserOrg : tUserOrgs) {
                UserVo userVo = userService.findByUid(tUserOrg.getUserId());
                users.add(userVo);
            }
            orgVo.setUsers(users);
            orgVOS.add(orgVo);
        }
        return ReturnDatas.ok(orgVOS);
    }
    @Transactional
    @Override
    public ReturnDatas save(OrgInputDTO orgInputDTO) {
        Date date = new Date();
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orgInputDTO.setCreateTime(sdf1.format(date));
        TOrg org = orgInputDTO.convertToEntity();
        baseMapper.insert(org);
        orgRoleService.updateOrgRole(org.getId(),orgInputDTO.getRoleIds());
        return ReturnDatas.ok(true);
    }
    @Transactional
    @Override
    public ReturnDatas update(OrgUpdateDTO orgUpdateDTO) {
        Date date = new Date();
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orgUpdateDTO.setUpdateTime(sdf1.format(date));
        TOrg org = orgUpdateDTO.convertToEntity();
        baseMapper.updateById(org);
        orgRoleService.updateOrgRole(org.getId(),orgUpdateDTO.getRoleIds());
        return ReturnDatas.ok(true);
    }

    @Override
    public ReturnDatas findById(Serializable id) {
        TOrg org = baseMapper.selectById(id);
        return ReturnDatas.ok(org);
    }

    @Override
    public ReturnDatas getOrgTree() {
        // 原始的数据
        List<OrgVO> orgs = getOrgs();

        ArrayList<OrgVO> orgVOList = new ArrayList<>();
        orgs.forEach(org -> {
            orgVOList.add(org);
        });
        // 最后的结果
        ArrayList<OrgVO> orgVOTree = new ArrayList<>();
        //先找到所有的一级权限
        for(OrgVO orgVO : orgVOList) {
            if(orgVO.getPid().equals("0")){
                orgVOTree.add(orgVO);
            }
        }
        for(OrgVO orgVO : orgVOTree) {
            List<OrgVO> child = getChild(orgVO.getId(), orgVOList);
            orgVO.setChildren(child);
        }
        return ReturnDatas.ok(orgVOTree);
    }
    //递归获取子组织
    public List<OrgVO> getChild(String id,List<OrgVO> allOrgs) {
        //子菜单
        List<OrgVO> childList = new ArrayList<>();
        for (OrgVO orgVO : allOrgs) {
            if(!orgVO.getPid().equals("0")){
                if(orgVO.getPid().equals(id)) {
                    childList.add(orgVO);
                }
            }
        }
        //递归终止的条件,没有子菜单时
        if(childList.size() == 0){
            return null;
        }
        //如果子菜单还有子菜单,把子菜单的子菜单再循环一遍
        for(OrgVO orgVO:childList) {
            if(!orgVO.getPid().equals("0")){
                orgVO.setChildren(getChild(orgVO.getId(),allOrgs));
            }
        }
        return childList;
    }
    //获取所有组织
    public List<OrgVO> getOrgs(){
        QueryWrapper queryWrapper = Wrappers.query();
        List<TOrg> orgs = baseMapper.selectList(queryWrapper);
        ArrayList<OrgVO> orgArrayList = new ArrayList<>();
        for (TOrg org : orgs) {
            OrgVO orgVO = copy2(org, OrgVO.class);
            orgArrayList.add(orgVO);
        }
        return orgArrayList;
    }
    @Override
    public ReturnDatas batchDelete(Collection<String> ids) {
        int i = baseMapper.deleteBatchIds(ids);
        if(i == 0){
            return ReturnDatas.error(false);
        }else {
            return ReturnDatas.ok(true);
        }

    }
/*    @Override
    public ReturnDatas deleteById(Serializable id) {
        baseMapper.deleteById(id);
        return ReturnDatas.ok(true);
    }*/

}
