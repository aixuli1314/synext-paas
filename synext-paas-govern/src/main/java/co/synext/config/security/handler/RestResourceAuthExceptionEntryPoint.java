package co.synext.config.security.handler;

import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import co.synext.common.constant.Constant;
import co.synext.common.base.resp.ReturnDatas;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class RestResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Setter
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
        ReturnDatas<String> result = new ReturnDatas<>();
        result.setStatusCode(Constant.ERROR_CODE);
        result.setMessage(authException.toString());
        PrintWriter printWriter = response.getWriter();
        printWriter.append(objectMapper.writeValueAsString(result));
    }
}
