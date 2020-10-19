package co.synext.config.nim.entity;

import com.google.gson.Gson;

import java.io.Serializable;


/**
 * <p>
 * NimBaseMsg 占位
 * </p>
 *
 * @author xu.ran
 * @since 2020-04-09
 */
public interface INimBaseMsg extends Serializable {

     Gson gson = new Gson();

    default String toJSONString() {
        return gson.toJson(this);
    }
}
