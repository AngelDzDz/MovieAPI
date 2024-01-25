package com.movieapi.movieapi.services;

import com.movieapi.movieapi.converters.UserConverter;
import com.movieapi.movieapi.dtos.LoginRequestDTO;
import com.movieapi.movieapi.dtos.LoginResponseDTO;
import com.movieapi.movieapi.entities.User;
import com.movieapi.movieapi.exceptions.GeneralServiceException;
import com.movieapi.movieapi.exceptions.NoDataFoundException;
import com.movieapi.movieapi.exceptions.ValidateServiceException;
import com.movieapi.movieapi.repository.UserRepository;
import com.movieapi.movieapi.validators.UserValidator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserService {
    @Value("${jwt.password}")
    private String jwtSecret;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserConverter userConverter = new UserConverter();

    public List<User> findAll() {
        try{
            return userRepository.findAll();
        }catch(ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(),e);
            throw e;
        }catch(Exception e) {
            log.error(e.getMessage(),e);
            throw new GeneralServiceException(e.getMessage(),e);
        }
    }

    public User findById(Long id) {
        try{
            return userRepository.findById(id).orElseThrow(()-> new NoDataFoundException("No existe el usuario!"));
        }catch(ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(),e);
            throw e;
        }catch(Exception e) {
            log.error(e.getMessage(),e);
            throw new GeneralServiceException(e.getMessage(),e);
        }
    }

    @Transactional
    public User createUser(User user) {
        try{
            UserValidator.signup(user);
            User foundUser = userRepository.findByUsername(user.getUsername()).orElse(null);
            if(foundUser != null) {
                throw new ValidateServiceException("El nombre de usuario ya existe");
            }
            String encoder = passwordEncoder.encode(user.getPassword());
            user.setPassword(encoder);
            return userRepository.save(user);

        }catch(ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(),e);
            throw e;
        }catch(Exception e) {
            log.error(e.getMessage(),e);
            throw new GeneralServiceException(e.getMessage(),e);
        }
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        try{
            User user = userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new ValidateServiceException("Usuario o password incorrectos"));
            if(!passwordEncoder.matches(request.getPassword(),user.getPassword())) {
                throw new ValidateServiceException("Usuario o password incorrectos");
            }
            return new LoginResponseDTO(userConverter.fromEntity(user),generateToken(user));
        }catch(ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(),e);
            throw e;
        }catch(Exception e) {
            log.error(e.getMessage(),e);
            throw new GeneralServiceException(e.getMessage(),e);
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new NoDataFoundException("El usuario no existe!"));

            userRepository.delete(user);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    public String generateToken(User user) {
        Date now = new Date();
        Date expiringDate = new Date(now.getTime()+(1000*60*60));

        SecretKey key = Jwts.SIG.HS256.key().build();
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(now)
                .expiration(expiringDate)
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()),Jwts.SIG.HS512).compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(jwtSecret.getBytes())).build().parseSignedClaims(token);
            return true;
        }catch(Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    public String getUsernameFromToken(String jwt) {
        try {
            return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(jwtSecret.getBytes())).build()
                    .parseSignedClaims(jwt)
                    .getPayload()
                    .getSubject();
        }catch(Exception e) {
            log.error(e.getMessage(),e);
            throw new ValidateServiceException("Invalid Token");
        }
    }
}
