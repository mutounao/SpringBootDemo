package cn.mutounao.demo.util.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class apiResult {
    private int code;
    private String message;
    private Object data;

    public static apiResult valueOf(@Nullable Object body){
        return new apiResult(errResult.SUCCESS.getCode(),errResult.SUCCESS.getMsg(),body);
    }
    public static apiResult errorOf(errResult e,String msg){
        return new apiResult(e.getCode(),e.getMsg(),msg);
    }
}

