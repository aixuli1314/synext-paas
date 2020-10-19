package co.synext.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import co.synext.vo.TableVo;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author xu.ran
 * @date 2020/5/19 18:11
 * @description: TODO
 */
public interface CodegenDao extends BaseMapper {

    List<TableVo> findTableList(Page page, @Param("tableName") String tableName);

    Map<String, String> findTable(String tableName);

    List<Map<String, String>> findTableColumns(String tableName);

}
