package co.synext.config;

import com.baomidou.mybatisplus.generator.config.ConstVal;

public interface CustomConstVal extends ConstVal {

    String DTO = "DTO";
    String VO = "VO";

    String TEMPLATE_INPUT_DTO = "/templates/input_dto.java";
    String TEMPLATE_UPDATE_DTO = "/templates/update_dto.java";
    String TEMPLATE_QUERY_DTO = "/templates/query_dto.java";
    String TEMPLATE_VO = "/templates/vo.java";

}
