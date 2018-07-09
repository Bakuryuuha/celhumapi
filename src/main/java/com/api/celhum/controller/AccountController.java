package com.api.celhum.controller;

import com.api.celhum.misc.ProjectStatus;
import com.api.celhum.model.Account;
import com.api.celhum.model.PasswordResetToken;
import com.api.celhum.service.AccountService;
import com.api.celhum.service.EmailService;
import com.api.celhum.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(path="/users", method = GET)
public class AccountController {
    private String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    private int checkPass(String plainPassword, String hashedPassword) {
        if (BCrypt.checkpw(plainPassword, hashedPassword))
            return 1;
        else
            return 0;
    }

    @Autowired
    private AccountService accountService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/tester")
    public @ResponseBody
    ResponseEntity<ProjectStatus> Tester(){
        return new ResponseEntity<ProjectStatus>(new ProjectStatus("Success...","tester"), HttpStatus.OK);
    }

    @PostMapping("/helo")
    public @ResponseBody
    String TesterHello(){
        return "HELLO WORLD";
    }

    @GetMapping("/checkemail/{email}")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity<ProjectStatus> CheckUsername(@PathVariable(value = "email") String email){
        Account acc = accountService.findByEmail(email);
        String checker = "available";
        if(acc != null){
            return new ResponseEntity<ProjectStatus>(new ProjectStatus("unavailable","Email Unavailable"), HttpStatus.OK);
        }
        return new ResponseEntity<ProjectStatus>(new ProjectStatus("available","Email Available"), HttpStatus.OK);

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/register")
    public @ResponseBody
    ResponseEntity<ProjectStatus> registNewAccount(@Valid @RequestBody Account account){

        Account acc = accountService.findByEmail(account.getEmail());
        if(acc == null) {
            account.setPassword(hashPassword(account.getPassword()));
            account.setId(UUID.randomUUID().toString());
            accountService.save(account);
            return new ResponseEntity<ProjectStatus>(new ProjectStatus("Success...","Success Register"), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<ProjectStatus>(new ProjectStatus("Failed...","Failed Register = Pick Different Email"), HttpStatus.OK);

        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public @ResponseBody ResponseEntity<ProjectStatus> loginAccountLive(@Valid @RequestBody Account account){
        //System.out.println(account);
        Account curAcc = accountService.findByEmail(account.getEmail());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isTrue = passwordEncoder.matches(account.getPassword(),curAcc.getPassword());
        //String passStr = hashPassword(account.getPassword());
        if(isTrue){
            //ketemu login
            return new ResponseEntity<ProjectStatus>(new ProjectStatus("Success","Good"), HttpStatus.OK);
        }else{
            return new ResponseEntity<ProjectStatus>(new ProjectStatus("Failed","Bad Credential"), HttpStatus.OK);
        }

    }

    @GetMapping("/details/{email}")
    @CrossOrigin(origins = "*")
    public Optional<Account> getAccById(@PathVariable(value = "email") String email) {
        Account curAcc = accountService.findByEmail(email);
        return accountService.findById(curAcc.getId());
    }

    @PostMapping("/update")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity<ProjectStatus> updateAccById(@RequestBody Account account) {
        Account curAcc = accountService.findByEmail(account.getEmail());
        Account accToUpdate = accountService.updateById(curAcc.getId());
        //accToUpdate.setFirst_name("TESTERRR");

        //update database profile
        accToUpdate.setFirst_name(account.getFirst_name());
        accToUpdate.setLast_name(account.getLast_name());
        accToUpdate.setDob(account.getDob());
        accToUpdate.setEmail(account.getEmail());
        accToUpdate.setGender(account.getGender());
        accToUpdate.setPassword(hashPassword(account.getPassword()));
        accToUpdate.setPhone(account.getPhone());

        accountService.save(accToUpdate);
        return new ResponseEntity<ProjectStatus>(new ProjectStatus("Success...","success"), HttpStatus.OK);
    }

    @GetMapping("/showall")
    @CrossOrigin(origins = "*")
    public @ResponseBody List<Account> showAllUsers() {
        return accountService.findAll();
    }

    @GetMapping("/forget")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity<ProjectStatus> forgetPasswordsendEmail() {

        emailService.send("ohohkevin@gmail.com","tester title","tester body");
        return new ResponseEntity<ProjectStatus>(new ProjectStatus("Success...","success"), HttpStatus.OK);
    }

    @PostMapping("/detailuser")
    @CrossOrigin(origins = "*")
    public @ResponseBody Account showUser(@RequestBody Account account) {
        Account curAcc = accountService.findByEmail(account.getEmail());
        return curAcc;
    }

    @RequestMapping(value = "/user/resetPassword",
            method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<ProjectStatus>  resetPassword(HttpServletRequest request,
                                         @RequestParam("email") String userEmail) {
        Account user = accountService.findByEmail(userEmail);
        if (user == null) {
            new ResponseEntity<ProjectStatus>(new ProjectStatus("Failed","not found"), HttpStatus.OK);
        }
        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);

        return new ResponseEntity<ProjectStatus>(new ProjectStatus("Success...","success"), HttpStatus.OK);
    }
}
