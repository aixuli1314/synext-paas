package co.synext.module.system.service.impl;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.common.base.vo.UserVo;
import co.synext.module.system.service.IUserService;
import co.synext.mybatis.entity.*;
import co.synext.mybatis.mapper.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.module.system.service.IUserExpertService;
import co.synext.module.system.dto.UserExpertInputDTO;
import co.synext.module.system.dto.UserExpertUpdateDTO;
import co.synext.module.system.dto.UserExpertQueryDTO;
import co.synext.common.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 专家信息表,是t_user表的扩展表,userType=3 服务实现类
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-08
 */
@Service("UserExpertServiceImpl")
public class UserExpertServiceImpl extends BaseService<TUserExpertMapper, TUserExpert> implements IUserExpertService {
    @Autowired
    private TUserExpertProfessionMapper professionMapper;
    @Autowired
    private TUserExpertQualificationMapper qualificationMapper;
    @Autowired
    private TUserExpertAwardMapper awardMapper;
    @Autowired
    private IUserService userService;

    @Override
    public ReturnDatas page(UserExpertQueryDTO userExpertQueryDTO) {
        TUserExpert tUserExpert = userExpertQueryDTO.convertToEntity();
        Page<TUserExpert> tUserExpertPage = baseMapper.selectPage(userExpertQueryDTO.page(), getLambdaQueryWrapper().setEntity(tUserExpert));
        for(int i=0;i<tUserExpertPage.getRecords().size();i++){
            QueryWrapper query = Wrappers.query().eq("userId",tUserExpertPage.getRecords().get(i).getId());
            ArrayList<TUserExpertAward> tUserExpertAwards = (ArrayList<TUserExpertAward>) awardMapper.selectList(query);
            ArrayList<TUserExpertQualification> tUserExpertQualifications = (ArrayList<TUserExpertQualification>) qualificationMapper.selectList(query);
            ArrayList<TUserExpertProfession> tUserExpertProfessions = (ArrayList<TUserExpertProfession>) professionMapper.selectList(query);
            tUserExpertPage.getRecords().get(i).setExpertAwardList(tUserExpertAwards);
            tUserExpertPage.getRecords().get(i).setExpertProfessionList(tUserExpertProfessions);
            tUserExpertPage.getRecords().get(i).setExpertQualificationList(tUserExpertQualifications);
        }
        return ReturnDatas.ok(tUserExpertPage);
    }

    @Override
    public ReturnDatas save(UserExpertInputDTO userExpertInputDTO) {
        UserVo byName = userService.findByNameAndMobile(userExpertInputDTO.getName(),userExpertInputDTO.getMobile());
        if(byName == null) {
            return ReturnDatas.error("请先进行用户注册");
        }
        TUserExpert tUserExpert = copy2(userExpertInputDTO, TUserExpert.class);
        tUserExpert.setId(byName.getId());
        List<TUserExpertProfession> expertProfessionList = userExpertInputDTO.getExpertProfessionList();
        List<TUserExpertQualification> expertQualificationList = userExpertInputDTO.getExpertQualificationList();
        List<TUserExpertAward> expertAwardList = userExpertInputDTO.getExpertAwardList();
        int insert = baseMapper.insert(tUserExpert);
        if(insert == 1) {
            for (TUserExpertProfession expertProfession : expertProfessionList) {
                expertProfession.setUserId(byName.getId());
                expertProfession.setName(byName.getUserName());
                expertProfession.setFromUnitName(tUserExpert.getUnitName());
                professionMapper.insert(expertProfession);
            }
            for (TUserExpertQualification expertQualification : expertQualificationList) {
                expertQualification.setUserId(byName.getId());
                expertQualification.setName(byName.getUserName());
                expertQualification.setFromUnitName(tUserExpert.getUnitName());
                qualificationMapper.insert(expertQualification);
            }
            for (TUserExpertAward expertAward : expertAwardList) {
                expertAward.setUserId(byName.getId());
                expertAward.setName(byName.getUserName());
                expertAward.setFromUnitName(tUserExpert.getUnitName());
                awardMapper.insert(expertAward);
            }
            return ReturnDatas.ok(true);
        }else {
            return ReturnDatas.error(false);
        }
    }

    @Override
    public ReturnDatas update(UserExpertUpdateDTO userExpertUpdateDTO) {
        TUserExpert userExpert = copy2(userExpertUpdateDTO,TUserExpert.class);
        List<TUserExpertProfession> expertProfessionList = userExpertUpdateDTO.getExpertProfessionList();
        List<TUserExpertQualification> expertQualificationList = userExpertUpdateDTO.getExpertQualificationList();
        List<TUserExpertAward> expertAwardList = userExpertUpdateDTO.getExpertAwardList();
        int i = baseMapper.updateById(userExpert);
        if ( i==1 ) {
            for (TUserExpertProfession expertProfession : expertProfessionList) {
                expertProfession.setUserId(userExpert.getId());
                expertProfession.setName(userExpert.getName());
                expertProfession.setFromUnitName(userExpert.getUnitName());
                professionMapper.updateById(expertProfession);
            }
            for (TUserExpertQualification expertQualification : expertQualificationList) {
                expertQualification.setUserId(userExpert.getId());
                expertQualification.setName(userExpert.getName());
                expertQualification.setFromUnitName(userExpert.getUnitName());
                qualificationMapper.updateById(expertQualification);
            }
            for (TUserExpertAward expertAward : expertAwardList) {
                expertAward.setUserId(userExpert.getId());
                expertAward.setName(userExpert.getName());
                expertAward.setFromUnitName(userExpert.getUnitName());
                awardMapper.updateById(expertAward);
            }
            return ReturnDatas.ok(true);
        }
        else {
            return ReturnDatas.error(false);
        }
    }

    @Override
    public ReturnDatas findById(Serializable id) {
        TUserExpert userExpert = baseMapper.selectById(id);
        QueryWrapper query = Wrappers.query().eq("userId",id);
        ArrayList<TUserExpertAward> tUserExpertAwards = (ArrayList<TUserExpertAward>) awardMapper.selectList(query);
        ArrayList<TUserExpertQualification> tUserExpertQualifications = (ArrayList<TUserExpertQualification>) qualificationMapper.selectList(query);
        ArrayList<TUserExpertProfession> tUserExpertProfessions = (ArrayList<TUserExpertProfession>) professionMapper.selectList(query);
        userExpert.setExpertAwardList(tUserExpertAwards);
        userExpert.setExpertQualificationList(tUserExpertQualifications);
        userExpert.setExpertProfessionList(tUserExpertProfessions);
        return ReturnDatas.ok(userExpert);
    }

    @Override
    public TUserExpert getById(String id) {
        TUserExpert userExpert = baseMapper.selectById(id);
        QueryWrapper query = Wrappers.query().eq("userId",id);
        ArrayList<TUserExpertAward> tUserExpertAwards = (ArrayList<TUserExpertAward>) awardMapper.selectList(query);
        ArrayList<TUserExpertQualification> tUserExpertQualifications = (ArrayList<TUserExpertQualification>) qualificationMapper.selectList(query);
        ArrayList<TUserExpertProfession> tUserExpertProfessions = (ArrayList<TUserExpertProfession>) professionMapper.selectList(query);
        userExpert.setExpertAwardList(tUserExpertAwards);
        userExpert.setExpertQualificationList(tUserExpertQualifications);
        userExpert.setExpertProfessionList(tUserExpertProfessions);
        return userExpert;
    }

    @Override
    public ReturnDatas batchDelete(Collection<String> ids) {
        ArrayList<String> expertAwardIds = new ArrayList<>();
        ArrayList<String> expertProfessionIds = new ArrayList<>();
        ArrayList<String> expertQualificationIds = new ArrayList<>();
        for (String id : ids) {
            QueryWrapper query = Wrappers.query().eq("userId",id);
            ArrayList<TUserExpertAward> tUserExpertAwards = (ArrayList<TUserExpertAward>) awardMapper.selectList(query);
            ArrayList<TUserExpertQualification> tUserExpertQualifications = (ArrayList<TUserExpertQualification>) qualificationMapper.selectList(query);
            ArrayList<TUserExpertProfession> tUserExpertProfessions = (ArrayList<TUserExpertProfession>) professionMapper.selectList(query);
            for (TUserExpertAward userExpertAward : tUserExpertAwards) {
                expertAwardIds.add(userExpertAward.getId());
            }
            for (TUserExpertProfession userExpertProfession : tUserExpertProfessions) {
                expertProfessionIds.add(userExpertProfession.getId());
            }
            for (TUserExpertQualification userExpertQualification : tUserExpertQualifications) {
                expertQualificationIds.add(userExpertQualification.getId());
            }
        }
        baseMapper.deleteBatchIds(ids);
        awardMapper.deleteBatchIds(expertAwardIds);
        qualificationMapper.deleteBatchIds(expertQualificationIds);
        professionMapper.deleteBatchIds(expertProfessionIds);
        return ReturnDatas.ok(true);

    }

/*
    @Override
    public ReturnDatas deleteById(Serializable id) {
        baseMapper.deleteById(id);
        return ReturnDatas.ok(true);
    }
*/

}
