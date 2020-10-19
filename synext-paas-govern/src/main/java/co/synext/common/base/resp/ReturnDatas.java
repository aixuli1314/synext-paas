package co.synext.common.base.resp;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonInclude;
import co.synext.common.constant.Constant;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author xu.ran
 * @date 2020/4/13 13:58
 * @description: TODO
 */

@Builder
@ToString
@Accessors(chain = true)
@AllArgsConstructor
public class ReturnDatas<T> implements Serializable {

    private static final long serialVersionUID = 2L;

    @Getter
    @Setter
    @Builder.Default
    private Integer statusCode  = Constant.SUCCESS_CODE;

    @Getter
    @Setter
    @Builder.Default
    private String message = "ok";

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    
    /**
     * 分页对象
     */
    @Getter
    @Setter
    private Page<T> page;

    public static ReturnDatas ok(){
       return ReturnDatas.builder().build();
    }


    public static ReturnDatas error(){
        return ReturnDatas.builder().statusCode(Constant.ERROR_CODE).message("error").build();
    }

    public static ReturnDatas ok(Object data){
        return ok(data,null);
    }
    public static ReturnDatas ok(Object data,String message){
    	return ok(data,message,null);
    }
    public static ReturnDatas ok(Object data,String message,Page page){
    	ReturnDatas build = ReturnDatas.builder().data(data).message(message).build();
    	build.setPage(page);
    	return build;
    }


    public static ReturnDatas error(Object data){
        return error(data,"error");
    }
    public static ReturnDatas error(Object data,String message){
    	return ReturnDatas.builder().data(data).statusCode(Constant.ERROR_CODE).message(message).build();
    }
    public static ReturnDatas error(Object data,String message,Page<Object> page){
    	ReturnDatas<Object> build = ReturnDatas.builder().data(data).statusCode(Constant.ERROR_CODE).message(message).build();
    	build.setPage(page);
    	return build;
    }

    public ReturnDatas() {
        super();
    }

    public ReturnDatas(Integer statusCode,String message) {
    	super();
    	this.statusCode = statusCode;
    	this.message = message;
    }
    public ReturnDatas(T data) {
        super();
        this.data = data;
    }

    public ReturnDatas(T data, String msg) {
        super();
        this.data = data;
        this.message = msg;
    }

    public ReturnDatas(Throwable e) {
        super();
        this.message = e.getMessage();
        this.statusCode = Constant.ERROR_CODE;
    }

    public String toJSONString(){
        return JSON.toJSONString(this);
    }

}
