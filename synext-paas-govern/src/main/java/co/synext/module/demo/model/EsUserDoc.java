package co.synext.module.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * <p>
 * ES USER INDEX
 * </p>
 *
 * @author xu.ran
 * @since 2020-04-10
 */

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "synext_yykj_es_data",type = "user")
public class EsUserDoc {

    @Id
    private Long uid;

    @Field(type = FieldType.Text,
            searchAnalyzer = "ik_smart",
            analyzer = "ik_max_word")
    private String email;

    @Field(type = FieldType.Text)
    private String phone;

    @Field(type = FieldType.Text,
            searchAnalyzer = "ik_smart",
            analyzer = "ik_max_word")
    private String username;

    @Field(type = FieldType.Text)
    private String password;

    @Field(type = FieldType.Integer)
    private Integer userType;

    @Field(type = FieldType.Integer)
    private Integer status;

    @Field(type = FieldType.Date)
    private Date createTime;

    @Field(type = FieldType.Text)
    private String createIp;

    @Field(type = FieldType.Date)
    private Date lastLoginTime;

    @Field(type = FieldType.Text)
    private String lastLoginIp;

    @Field(type = FieldType.Date)
    private Integer loginNum;



}
