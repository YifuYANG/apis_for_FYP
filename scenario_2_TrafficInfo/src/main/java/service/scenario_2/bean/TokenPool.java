package service.scenario_2.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TokenPool {

    //pool object maps <int userId -> String token>
    private Map<Integer, String> pool = new HashMap<>();

    public String generateToken() {
        return UUID.randomUUID().toString();
    }

    public int getUserIdByToken(String token) {
        for (Map.Entry<Integer, String> entry : pool.entrySet()) {
            if (entry.getValue().equals(token))
                return entry.getKey();
        }
        return -1;
    }

    public String getTokenByUserId(Long id) {
        return pool.get(id);
    }

    public void login(int userid, String token) {
        pool.put(userid, token);
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
