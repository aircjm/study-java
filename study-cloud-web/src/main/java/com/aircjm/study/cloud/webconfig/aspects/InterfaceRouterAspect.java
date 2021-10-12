package com.aircjm.study.cloud.webconfig.aspects;

import com.aircjm.study.cloud.webconfig.annotation.InterfaceRouter;
import com.aircjm.study.cloud.webconfig.factory.CacheBeansFactory;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

/**
 * 路由切面
 *
 * @author aircjm
 */
@Aspect
@Component
public class InterfaceRouterAspect {


    protected static final Logger log = LoggerFactory.getLogger(InterfaceRouterAspect.class);


    @Resource
    private HttpServletRequest httpServletRequest;

    public static final String PATH = "/limon";


    @Pointcut("@annotation(interfaceRouterAspect)")
    public void interfaceRouterAspect(InterfaceRouter interfaceRouterAspect) {
    }


    @Around(value = "interfaceRouterAspect(interfaceRouter)", argNames = "point,interfaceRouter")
    @SuppressWarnings("unchecked")
    public <T> Object around(ProceedingJoinPoint point, InterfaceRouter interfaceRouter) throws Throwable {
        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        // 获取参数签名
        CodeSignature codeSignature = ((CodeSignature) point.getSignature());
        // Method
        Method sourceMethod = methodSignature.getMethod();
        // 参数
        Object[] args = point.getArgs();
        // Parameter
        Parameter[] parameters = sourceMethod.getParameters();

        // 如果未配置url和开关,则走原逻辑
        Map<String, Integer> routeUrlMap = Maps.newHashMap();
        if (MapUtils.isEmpty(routeUrlMap)) {
            return point.proceed();
        }

        // 获取client的bean
        T t = (T) CacheBeansFactory.getBeanByType(interfaceRouter.clientClass());
        if (Objects.isNull(t)) {
            throw new Exception("获取clientBean:" + interfaceRouter.getClass() + "失败");
        }

        // 获取Method
        String methodName = interfaceRouter.methodName();
        Method method;
        String requestUri = httpServletRequest.getRequestURI().replace(PATH, "");
        if (StringUtils.isNotBlank(methodName)) {
            // 如果配置了方法名，按照方法名匹配
            method = t.getClass().getMethod(methodName, codeSignature.getParameterTypes());
        } else {
            // 没有配置方法名，按照原请求路径匹配
            method = getMethod(t, interfaceRouter.clientClass().getMethods(), requestUri);
        }
        log.info("方法名为:{}", method.getName());

//        /*
//         * 获取开关
//         */
//        int switchOpen;
//        List<RouteUrlVo> sortList = Lists.newArrayList();
//        if (StringUtils.isNotBlank(interfaceRouter.switchUrl())) {
//            Map.Entry<String, Integer> et = routeUrlMap.entrySet().stream().filter(entry -> StringUtils.equalsIgnoreCase(interfaceRouter.switchUrl(), entry.getKey())).findAny().orElse(null);
//            switchOpen = Objects.isNull(et) ? 0 : et.getValue();
//        } else {
//            routeUrlMap.forEach((url, value) -> {
//                url = StringUtils.replace(url, "**", "");
//                if (StringUtils.startsWith(requestUri, url)) {
//                    RouteUrlVo vo = new RouteUrlVo();
//                    vo.setOrder(url.length());
//                    vo.setSwitchOpen(value);
//                    sortList.add(vo);
//                }
//            });
//            sortList.sort(Comparator.comparing(RouteUrlVo::getOrder).reversed());
//            RouteUrlVo routeUrlVo = null;
//            if (CollectionUtils.isNotEmpty(sortList)) {
//                routeUrlVo = sortList.get(0);
//            }
//            switchOpen = Objects.isNull(routeUrlVo) ? 0 : routeUrlVo.getSwitchOpen();
//        }
//        log.info("开关为:{}", switchOpen);
//        if (1 != switchOpen) {
//            return point.proceed();
//        }
        return method.invoke(t, args);
    }

    /**
     * 获取Method
     *
     * @param t          client对应的bean
     * @param methods    methods
     * @param requestUri 匹配路径
     * @return 方法
     * @throws Throwable 异常
     */
    private <T> Method getMethod(T t, Method[] methods, String requestUri) throws Throwable {
        String value;
        for (Method method : methods) {
            if (method.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                value = requestMapping.value().length > 0 ? requestMapping.value()[0] : null;
            } else if (method.isAnnotationPresent(PostMapping.class)) {
                PostMapping postMapping = method.getAnnotation(PostMapping.class);
                value = postMapping.value().length > 0 ? postMapping.value()[0] : null;
            } else if (method.isAnnotationPresent(GetMapping.class)) {
                GetMapping getMapping = method.getAnnotation(GetMapping.class);
                value = getMapping.value().length > 0 ? getMapping.value()[0] : null;
            } else {
                throw new Exception("不支持的注解类型");
            }
            if (StringUtils.isBlank(value)) {
                throw new Exception("requestUri为空");
            }
            if (StringUtils.equalsIgnoreCase(value, requestUri)) {
                return t.getClass().getMethod(method.getName(), method.getParameterTypes());
            }
        }
        throw new Exception("method不存在");
    }
}
