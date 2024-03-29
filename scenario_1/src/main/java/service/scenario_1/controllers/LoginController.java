package service.scenario_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.scenario_1.bean.TokenPool;
import service.scenario_1.entity.User;
import service.scenario_1.repository.UserRepository;
import service.scenario_1.vo.Loginform;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    TokenPool tokenPool;
    @RequestMapping(value="/authentication/pass",method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map> login(@RequestBody Loginform loginform){
        Map<String,String> map = new HashMap<>();
        System.out.println(loginform.getDeviceid()+"====="+loginform.getPassword());
        User user = userRepository.findByUserBytrustdvice(loginform.getDeviceid());
        if(loginform.getPassword().equals(user.getPassword())){
            String token=tokenPool.generateToken();
            tokenPool.login(loginform.getDeviceid(),token);
            map.put("token",token);
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        } else {
            map.put("token",null);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }
}
