package service.scenario_1.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TokenPool {

    //pool object maps <Long userId -> String token>
    private Map<String, String> pool = new HashMap<>();

    public String generateToken() {
        return UUID.randomUUID().toString();
    }

    public String getUserIdByToken(String token) {
        for (Map.Entry<String, String> entry : pool.entrySet()) {
            if (entry.getValue().equals(token))
                return entry.getKey();
        }
        return null;
    }

    public String getTokenByUserId(Long id) {
        return pool.get(id);
    }

    public void login(String device, String token) {
        pool.put(device, token);
    }



    public boolean containsUserId(Long userId) {
        return pool.containsKey(userId);
    }

    public boolean containsToken(String token) {
        return pool.containsValue(token);
    }

    public String toString() {
        return pool.toString();
    }

}
