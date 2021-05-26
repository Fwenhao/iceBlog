package cn.fwhspace.iceblog.config.outputdto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/25 15:56
 * @description 统一全局返回体格式
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "全局出参对象",description = "统一全体出参返回体格式")
public class GlobalOutPutDTO {
    /**
     * 返回状态标识
     * */
    private Integer code;
    /**
     * 返回信息
     * */
    private String msg;
    /**
     * 返回体实体(对象，集合，数组)
     * */
    private Object data;

    /**
     * @description : 〈失败时返回〉
     * @param code 1
     * @param msg 2
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     */
    public static GlobalOutPutDTO fail(Integer code, String msg){
        return GlobalOutPutDTO.builder().code(code).msg(msg).build();
    }

    /**
     * @description : 〈成功时返回对象〉
     * @param code 1
     * @param msg 2
     * @param data 3
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     */
    public static GlobalOutPutDTO success(Integer code, String msg, Object data){
        return GlobalOutPutDTO.builder().code(code).msg(msg).data(data).build();
    }

    /**
     * @description : 〈成功时返回数组〉
     * @param code 1
     * @param msg 2
     * @param data 3
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     */
    public static GlobalOutPutDTO success(Integer code, String msg, Object[] data){
        return GlobalOutPutDTO.builder().code(code).msg(msg).data(data).build();
    }

    /**
     * @description : 〈成功时返回集合〉
     * @param code 1
     * @param msg 2
     * @param data 3
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     */
    public static GlobalOutPutDTO success(Integer code, String msg, List<Object> data){
        return GlobalOutPutDTO.builder().code(code).msg(msg).data(data).build();
    }

    /**
     * @description : 〈成功时无返回集〉
     * @param code 1
     * @param msg 2
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     */
    public static GlobalOutPutDTO success(Integer code, String msg){
        return GlobalOutPutDTO.builder().code(code).msg(msg).build();
    }
}
