package com.example.Springboot.service;

import com.example.Springboot.domain.Message;
import com.example.Springboot.domain.Role;
import com.example.Springboot.domain.User;
import com.example.Springboot.domain.UserInfo;
import com.example.Springboot.repos.MessageRepo;
import com.example.Springboot.repos.UserInfoRepo;
import com.example.Springboot.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MailSender mailSender;

    @Autowired
    MessageRepo messageRepo;


    @Autowired
    UserInfoRepo userInfoRepo;

    @Value("${hostname}")
    private String hostname;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null){
           throw new  UsernameNotFoundException("User not exists");
        }
        return user;
    }
    public boolean addUser(User user){
        User userFromBD = userRepo.findByUsername(user.getUsername());
        if(userFromBD !=null){
           return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        sendMessage(user);

        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByactivationCode(code);
        if(user == null){
            return  false;
        }
        user.setActivationCode(null);
        userRepo.save(user);
        return true;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
    }

    public void updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();
        boolean isEmailChanged= (email !=null && !email.equals(userEmail)) || (userEmail !=null && !userEmail.equals(email));
        if(isEmailChanged){
            user.setEmail(email);
            if(!StringUtils.isEmpty(email)){
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }
        if(!StringUtils.isEmpty(password)){
            user.setPassword(password);
        }
        userRepo.save(user);
        if(isEmailChanged){
        sendMessage(user);
        }
    }
    public void sendMessage(User user){
        if(!StringUtils.isEmpty(user.getEmail())){
            String message = String.format(
                    "Hi, %s \n"+
                            "Welcome,Before we continue please confirm your account link:http://%s/activate/%s",
                    user.getUsername(),
                    hostname,
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(),"Activation code",message);
        }
    }

    public void subscribe(User currentUser, User user) {
        user.getSubscribers().add(currentUser);
        userRepo.save(user);
    }

    public void unsubscribe(User currentUser, User user) {
        user.getSubscribers().remove(currentUser);
        userRepo.save(user);
    }

    public int getLikesAmount(User currentUser) {
        List<Message> messageList = messageRepo.findByUser(currentUser);
        int amount = 0;
        for(Message message : messageList){
            amount += message.getLikes().size();
        }
        return amount;
    }


    public Message getMostPopularMessage(User currentUser) {
        List<Message> messageList = messageRepo.findByUser(currentUser);
        Message message = new Message();
        for(Message m : messageList){
            if(m.getLikes().size() > message.getLikes().size()){
                message = m;
            }
        }
        return message;
    }

    public void saveUserInfo(UserInfo userInfo, String userName, String userLastName, String country, String aboutme, LocalDate dateOfBirth, String logourl, User currentuser)
    {
        if(userInfo != null && userInfo.getUserId().equals(currentuser))
        {
            if (!StringUtils.isEmpty(userName)) {
                userInfo.setUserName(userName);
            }
            if (!StringUtils.isEmpty(userLastName)) {
                userInfo.setUserLastName(userLastName);
            }
            if (!StringUtils.isEmpty(country)) {
                userInfo.setCountry(country);
            }
            if (!StringUtils.isEmpty(userName)) {
                userInfo.setUserName(userName);
            }
            if (!StringUtils.isEmpty(aboutme)) {
                userInfo.setAboutMe(aboutme);
            }
            if (!StringUtils.isEmpty(dateOfBirth)) {
                userInfo.setDateOfBirth(dateOfBirth);
            }
            if (!StringUtils.isEmpty(logourl)) {
                userInfo.setLogoUrl(logourl);
            }
            userInfoRepo.saveAndFlush(userInfo);

        }
        if(userInfo == null) {
            UserInfo user = new UserInfo(userName, userLastName, country, aboutme, logourl, dateOfBirth);
            user.setUserId(currentuser);
            userInfoRepo.saveAndFlush(user);
        }
    }
}
