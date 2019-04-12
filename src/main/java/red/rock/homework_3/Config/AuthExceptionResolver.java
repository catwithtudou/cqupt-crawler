package red.rock.homework_3.Config;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AuthExceptionResolver
 * @Description 自定义配置错误异常页面
 * @Authot tudou
 * @Date 2019/4/13 1:13
 * @Version 1.0
 **/
public class AuthExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mv=new ModelAndView(new MappingJackson2JsonView());
        Map<String,String> map=new HashMap<>();
        map.put("status","error");
        mv.addAllObjects(map);
        return mv;
    }
}
