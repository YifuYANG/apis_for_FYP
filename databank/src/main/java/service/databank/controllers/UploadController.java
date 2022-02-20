package service.databank.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.databank.entity.UserInfo;
import service.databank.repository.UserinfoRepository;
import service.databank.vo.Databankform;

import java.util.List;

@RestController
public class UploadController {
    @Autowired
    UserinfoRepository userinfoRepository;
    @RequestMapping(value="/uploaduserinfo",method= RequestMethod.POST)
    public ResponseEntity<Boolean> uploadData(@RequestBody UserInfo userInfo){
        userinfoRepository.save(userInfo);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    @RequestMapping(value="/finddriver",method= RequestMethod.POST)
    public ResponseEntity<Boolean> uploadData(@RequestBody Databankform databankform){
        List<UserInfo> userInfos =userinfoRepository.findByDriverLicense(databankform.getDriverlicense());
        System.out.println(databankform.getDriverlicense());
        for(UserInfo info: userInfos){
            if(info.getCarplate().equals(databankform.getDeviceid())){
                return new ResponseEntity<>(true, HttpStatus.FOUND);
            }
        }
        return new ResponseEntity<>(false, HttpStatus.FOUND);
    }
}
