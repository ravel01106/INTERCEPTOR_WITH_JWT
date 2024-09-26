package com.interceptor.example.auth.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interceptor.example.auth.application.AuthService;
import com.interceptor.example.auth.domain.JwtAuthorization;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;
import java.util.HashMap;

@Component("authInterceptor")
public class AuthInterceptor implements HandlerInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

  private static final String AUTH_HEADER_PARAMETER_AUTHERIZATION = "authorization";

	private static final String AUTH_HEADER_PARAMETER_BEARER = "Bearer ";

  @Autowired
  AuthService authService;

  public boolean preHandler(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    String jwtAuthToken = null;
    JwtAuthorization jwtAuthorization = null;

    jwtAuthToken = request.getHeader(AUTH_HEADER_PARAMETER_AUTHERIZATION).replace(AUTH_HEADER_PARAMETER_BEARER, "");

    jwtAuthorization = authService.getJwtAuthorization(jwtAuthToken);

    logger.info("token: " + jwtAuthToken);

    if (authService.validateToken(jwtAuthorization)) {
      return true;
    }

    Map<String, String> json = new HashMap<>();
    json.put("message", "No tienes acceso a esta página!!");
    ObjectMapper mapper = new ObjectMapper();
    String jsonString = mapper.writeValueAsString(json);
    response.setContentType("application/json");
    response.setStatus(401);
    response.getWriter().write(jsonString);
    return false;

  }

}
