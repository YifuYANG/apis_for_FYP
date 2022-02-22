package service.scenario_2.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.scenario_2.access.RestrictUserAccess;
import service.scenario_2.bean.TokenPool;
import service.scenario_2.constant.UserLevel;

@Controller
public class TrafficInfoController {
    @Autowired
    TokenPool tokenPool;
    @RestrictUserAccess(requiredLevel = UserLevel.admin)
    @RequestMapping(value="/traffic",method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Boolean> requiretraffic(@RequestHeader("token") String token){
        System.out.println(tokenPool.getUserIdByToken(token));
        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
    }
}
