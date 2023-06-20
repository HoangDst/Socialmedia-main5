package com.example.service;


import com.hm.social.tables.pojos.User;
import com.example.jwt.JwtUltis;
import com.example.IUserRepo;
import com.example.data.mapper.UserMapperImpl;
import com.example.data.request.UserLoginRequest;
import com.example.data.request.UserSignInRequest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
@ComponentScan("com.jwt")
public class AuthenticationServiceImpl implements IAuthenticationService{
    private final IUserRepo userRepo;
    private final JwtUltis jwtUltis;
    private final UserMapperImpl userMapper;
    private final AuthenticationManager authenticationManager;



    public AuthenticationServiceImpl(IUserRepo userRepo, JwtUltis jwtUltis,
                                     UserMapperImpl userMapper,AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.jwtUltis = jwtUltis;
        this.userMapper = userMapper;
        this.authenticationManager=authenticationManager;
    }


    @Override
    public String checkValidUser(UserLoginRequest userLoginRequest) {
        User user = userRepo.findUserByUserAccount(userLoginRequest.getUserAccount());
        boolean isValidPs = false ;
        if (userLoginRequest.getPassword()==user.getPassword()){
            isValidPs = true;
        }
    //    final boolean isValidPs = BCrypt.checkpw(userLoginRequest.getPassword(), user.getPassword());
        if (isValidPs){
            return "No account";
        }
        return userLoginRequest.getUserAccount();
    }

    @Override
    public String authenticate(UserLoginRequest userLoginRequest) {
        String userAccount = checkValidUser(userLoginRequest);
        if(userAccount == "No account"){
            return "No Account";
        }
        return jwtUltis.generateToken(userAccount);
    }

    @Override
    public boolean insertUSer(UserSignInAndUpdateRequest userSignInAndUpdateRequest) {
        User user= userMapper.toEnity(userSignInAndUpdateRequest);
        boolean insertUser = userRepo.insertUser(user);
        return insertUser;
    }
}
