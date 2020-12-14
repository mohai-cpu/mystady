//package hello.utils;
//
//import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
//import com.alibaba.csp.sentinel.slots.block.BlockException;
//import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
//import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
//import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
//import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
//import com.alibaba.fastjson.JSONObject;
//import com.taikang.common.Result;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @Description
// * @Author:itw_zhangjian04
// * @Date： 2020/11/2013:44
// */
//public class SentinelUrlBlockHandler implements UrlBlockHandler {
//    @Override
//    public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {
//        httpServletResponse.setHeader("content-Type","text/html;charset=utf-8");
//        Result result = new Result();
//        if(e instanceof FlowException){
//            result.setCode(2001);
//            result.setMsg("限流-异常");
//        }
//        else if(e instanceof DegradeException){
//            result.setCode(2002);
//            result.setMsg("降级-异常");
//        }
//        else if(e instanceof ParamFlowException){
//            result.setCode(2003);
//            result.setMsg("热点-异常");
//        }
//        else if(e instanceof SystemBlockException){
//            result.setCode(2004);
//            result.setMsg("系统规则-异常");
//        }
//        else if(e instanceof AuthorityException){
//            result.setCode(2005);
//            result.setMsg("授权-异常");
//        }else{
//            result.setCode(2006);
//            result.setMsg("未知-异常");
//        }
//        httpServletResponse.setStatus(200);
//        //httpServletResponse.setContentType("text/html;charset=utf-8");
//        httpServletResponse.getWriter().println(JSONObject.toJSONString(result));
//    }
//}
