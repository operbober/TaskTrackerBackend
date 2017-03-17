package by.tasktracker.config;


import by.tasktracker.entity.User;
import by.tasktracker.security.Permission;
import by.tasktracker.security.PermissionChecker;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

@Component
public class CommonInterceptor implements HandlerInterceptor{

    @Autowired
    private ApplicationContext context;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Method method = ((HandlerMethod) handler).getMethod();

        if (method.getAnnotation(Permission.class) != null) {
            Class<? extends PermissionChecker> permCheckerClass = method.getAnnotation(Permission.class).value();
            PermissionChecker permChecker = context.getBean(permCheckerClass);
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Gson gson = new Gson();
            Object object = gson.fromJson(getBody(request), getParameterClass(permCheckerClass));
            permChecker.checkPermission(user, object);
        }

        return true;
    }

    private Class<?> getParameterClass(Class<? extends PermissionChecker> permCheckerClass) {
        return (Class<?>)((ParameterizedType)permCheckerClass.getGenericInterfaces()[0]).getActualTypeArguments()[0];
    }

    private static String getBody(HttpServletRequest request) throws IOException {

        String body;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }

        body = stringBuilder.toString();
        return body;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
            throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
            throws Exception {}





}
