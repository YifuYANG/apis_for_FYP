package service.scenario_2.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.scenario_2.bean.TokenPool;
import service.scenario_2.entity.User;
import service.scenario_2.repository.UserRepository;
import service.scenario_2.vo.Loginform;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenPool tokenPool;

    @RequestMapping(value="/login",method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map> login(@RequestBody Loginform loginform){
        User user = userRepository.findByUserbyusername(loginform.getUsername());
        if(loginform.getPassword().equals(user.getPassword())){
            String token=tokenPool.generateToken();
            tokenPool.login(user.getId(),token);
            Map<String,String> map = new HashMap<>();
            map.put("token",token);
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        } else {
            return null;
        }
    }
}
