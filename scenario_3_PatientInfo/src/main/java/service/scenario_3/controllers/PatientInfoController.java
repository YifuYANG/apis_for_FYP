package service.scenario_3.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.scenario_3.access.RestrictUserAccess;
import service.scenario_3.constant.UserLevel;


@Controller
public class PatientInfoController {

    @RestrictUserAccess(requiredLevel = UserLevel.admin)
    @RequestMapping(value="/patientInfo",method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Boolean> requirepatientinfo(@RequestHeader("token") String token){
        System.out.println("Hello "+ token);
        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
    }
}
