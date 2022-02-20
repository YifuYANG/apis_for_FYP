package service.scenario_1.access;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.scenario_1.bean.TokenPool;
import service.scenario_1.constant.UserLevel;
import service.scenario_1.entity.User;
import service.scenario_1.repository.UserRepository;


import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class RestrictUserAccessAspect {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenPool tokenPool;

    @Around("@annotation(service.scenario_1.access.RestrictUserAccess)")
    public Object restrictUserAccess(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        UserLevel userLevel = method.getAnnotation(RestrictUserAccess.class).requiredLevel();
        try {
            String token = (String) joinPoint.getArgs()[0];
            String trustdvice=tokenPool.getUserIdByToken(token);
            User user=userRepository.findByUserBytrustdvice(trustdvice);
            if(user==null){
                throw new Exception("User doest login");
            } else if(user.getUserLevel() != userLevel){
                throw new Exception("Access denied");
            } else {
                return joinPoint.proceed();
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
            return e.getMessage();
        }

    }
}
