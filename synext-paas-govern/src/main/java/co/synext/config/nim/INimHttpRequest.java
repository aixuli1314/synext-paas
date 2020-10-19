package co.synext.config.nim;

import co.synext.config.nim.entity.NimBaseReqEntity;
import co.synext.config.nim.entity.NimBaseRespEntity;
import org.springframework.http.HttpEntity;

/**
 * <p>
 * NimHttpRequest
 * </p>
 *
 * @author xu.ran
 * @since 2020-04-09
 */
public interface INimHttpRequest<R extends NimBaseReqEntity>  {

    HttpEntity builderHttpEntity(R requestEntity);

    <T extends NimBaseRespEntity> T execute(String url, R requestEntity, Class<T> responseClass);
}
