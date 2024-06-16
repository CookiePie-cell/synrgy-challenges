package com.challenge7.auth.service;

import com.challenge7.auth.model.dto.*;
import com.challenge7.auth.model.entity.AuthUser;
import com.challenge7.auth.model.entity.Role;
import com.challenge7.auth.repository.RoleRepository;
import com.challenge7.auth.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmailSender emailSender;

    @Value("${AUTHURL:}")//FILE_SHOW_RUL
    private String AUTHURL;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public String register(RegisterRequestDto request, Boolean isEnabled) {
        Optional<AuthUser> currUser = userAuthRepository.checkExistingEmail(request.getUsername());
        if (currUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username sudah ada");
        }

        AuthUser user = new AuthUser();
        user.setUsername(request.getUsername().toLowerCase());
        user.setFullname(request.getFullname());

        // Encode the password
        String password = encoder.encode(request.getPassword().replaceAll("\\s+", ""));
        user.setPassword(password);

        // Set roles for the user
        List<Role> roles = roleRepository.findByNameIn(new String[]{request.getRole()});
        user.setRoles(roles);

        if (!user.isEnabled()) {
            emailSender.sendEmailRegister(user);
            return "Silahkan cek email untuk aktivasi akun";
        } else {
            userAuthRepository.save(user);
            return "Akun berhasil dibuat";
        }


    }

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        AuthUser user = userAuthRepository.findOneByUsername(request.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "bad credentials"));

        if (!user.isEnabled()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "account is disabled");

        if (!encoder.matches(request.getPassword(), user.getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bad credentials");

        String url = AUTHURL + "?username=" + request.getUsername() +
                "&password=" + request.getPassword() +
                "&grant_type=password" +
                "&client_id=my-client-web" +
                "&client_secret=password";

        ResponseEntity<Map> response = restTemplateBuilder.build().exchange(url, HttpMethod.POST, null, new ParameterizedTypeReference<Map>() {
        });

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new ResponseStatusException(response.getStatusCode(), (String) Objects.requireNonNull(response.getBody()).get("error_description"));
        }

        return LoginResponseDto.builder()
                .access_token((String) Objects.requireNonNull(response.getBody()).get("access_token"))
                .refresh_token((String) Objects.requireNonNull(response.getBody()).get("refresh_token"))
                .expires_in(Long.valueOf((Integer) Objects.requireNonNull(response.getBody()).get("expires_in")))
                .token_type((String) Objects.requireNonNull(response.getBody()).get("token_type"))
                .scope((String) Objects.requireNonNull(response.getBody()).get("scope"))
                .jti((String) Objects.requireNonNull(response.getBody()).get("jti"))
                .message(null)
                .build();

    }

    @Override
    public void confirmOTP(String otp) {

    }

    @Override
    public void forgetPassword(ForgetPasswordRequestDto request) {

    }

    @Override
    public void changePassword(ChangePasswordRequestDto request) {

    }
}
