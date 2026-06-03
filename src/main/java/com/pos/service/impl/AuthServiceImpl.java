package com.pos.service.impl;

import com.pos.configuration.JwtProvider;  
import com.pos.exception.UserException;
import com.pos.mapper.UserMapper;
import com.pos.modal.UserRole;
import com.pos.modal.Users;
import com.pos.payload.dto.userDto;
import com.pos.payload.response.AuthResponse;
import com.pos.repository.UsersRepository;
import com.pos.service.AuthService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
 
@Service
public class AuthServiceImpl implements AuthService {



    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserImplementation customUserImplementation;

    public AuthServiceImpl(UsersRepository usersRepository,
                           PasswordEncoder passwordEncoder,
                           JwtProvider jwtProvider,
            CustomUserImplementation customUserImplementation) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;

        this.customUserImplementation = customUserImplementation;
    }

    @Override
    public AuthResponse signup(userDto UserDto) throws UserException  {
        Users users=usersRepository.findByEmail(UserDto.getEmail());

        if(users!=null){

            throw  new UserException("User Already Exist with this email ");

        }
        if(UserDto.getRole().equals(UserRole.ROLE_ADMIN)){
            throw  new UserException("Role Admin is not allowed");
        }

        Users newUser=new Users();
        newUser.setEmail(UserDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(UserDto.getPassword()));
        newUser.setRole(UserDto.getRole());
        newUser.setFullName(UserDto.getFullName());
        newUser.setPhone(UserDto.getPhone());
        newUser.setLastLogin(LocalDateTime.now());
        newUser.setCreatedAt(LocalDateTime.now());

        Users savedUser=usersRepository.save(newUser);

        Authentication authentication=new
                UsernamePasswordAuthenticationToken(UserDto.getEmail(), UserDto.getPassword());

        SecurityContextHolder
                .getContext().setAuthentication(authentication);

        String jwt=jwtProvider.generateToken(authentication);

        AuthResponse authResponse=new AuthResponse();
        authResponse.setToken(jwt);
        authResponse.setMessage("Registered Successfully");
        authResponse.setUser(UserMapper.toDTO(savedUser));
        return authResponse;
    }

    @Override
    public AuthResponse login(userDto UserDto) throws UserException {
        String email=UserDto.getEmail();
        String password=UserDto.getPassword();

        Authentication authentication=authenticate(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Collection<? extends GrantedAuthority> authorities = authentication
                .getAuthorities();
        String role=authorities.iterator().next().getAuthority();
        String jwt=jwtProvider.generateToken(authentication);


        Users users=usersRepository.findByEmail(email);
        users.setLastLogin(LocalDateTime.now());
        usersRepository.save(users);
        AuthResponse authResponse=new AuthResponse();
        authResponse.setToken(jwt);
        authResponse.setMessage("Login Successfully");
        authResponse.setUser(UserMapper.toDTO(users));
        return authResponse;
        

    }

    private Authentication authenticate(String email, String password) throws UserException {

        UserDetails userDetails=customUserImplementation
                .loadUserByUsername(email);
        if(userDetails==null){
            throw new UserException("email id does not exist");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){

            throw new UserException("password does not match");

        }

        return new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
    }
}
