package co.synext.javafx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import co.synext.dao.CodegenDao;
import co.synext.vo.TableVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TableService {

    @Autowired
    private CodegenDao codegenDao;

    @Transactional
    public Page<TableVo> getPage(Integer pageIndex, Integer pageSize, String tableName) {
        Page<TableVo> page = new Page(pageIndex, pageSize);
        page.setRecords(codegenDao.findTableList(page, tableName));
        return page;
    }

}
