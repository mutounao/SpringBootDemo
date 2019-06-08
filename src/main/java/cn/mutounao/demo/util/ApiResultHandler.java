package cn.mutounao.demo.util;

import cn.mutounao.demo.util.pojo.apiResult;
import cn.mutounao.demo.util.pojo.errResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;

@Order(10000)
@ControllerAdvice
public class ApiResultHandler implements ResponseBodyAdvice {
    private ThreadLocal<ObjectMapper> mapperThreadLocal = ThreadLocal.withInitial(ObjectMapper::new);

    @Value("${api.result.filter}")
    private String configUrls;

    private static final Class[] annos = {
            RequestMapping.class,
            GetMapping.class,
            PostMapping.class,
            DeleteMapping.class,
            PutMapping.class
    };

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        AnnotatedElement element = returnType.getAnnotatedElement();
        return Arrays.stream(annos).anyMatch(anno -> anno.isAnnotation() && element.isAnnotationPresent(anno));
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Object out;
        String url = request.getURI().toString();
        if (checkUrl(url)) {
            ObjectMapper mapper = mapperThreadLocal.get();
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            if (body instanceof apiResult) {
                out = body;
            } else if (body instanceof errResult) {
                errResult err = (errResult) body;
                out = new apiResult(err.getCode(), err.getMsg(), null);
            } else if (body instanceof String) {
                apiResult result = apiResult.valueOf(body);
                try {
                    out = mapper.writeValueAsString(result);
                } catch (JsonProcessingException e) {
                    out = apiResult.errorOf(errResult.JSON_PARSE_ERROR, e.getMessage());
                }
            } else {
                out = apiResult.valueOf(body);
            }
        }else{
            out = body;
        }
        return out;
    }

    private boolean checkUrl(String checkLink){
        String[] urls = configUrls.split(",");
        boolean flag = true;
        for (int i = 0; i < urls.length; i++) {
            if (checkLink.contains(urls[i])){
                flag = false;
                break;
            }
        }
        return flag;
    }
}
