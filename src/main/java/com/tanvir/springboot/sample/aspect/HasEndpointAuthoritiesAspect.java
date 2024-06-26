//package com.tanvir.springboot.sample.aspect;
//
//import com.tanvir.springboot.sample.exception.ApiException;
//import com.tanvir.springboot.sample.exception.DefaultExceptionReason;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//import java.util.Objects;
//import java.util.stream.Stream;
//
//@Aspect
//@Component
//@Slf4j
//public class HasEndpointAuthoritiesAspect {
//
//    @Before("execution(* com.tanvir.springboot.sample.controller.*Controller.*(..)) && @annotation(authorities)")
//    public void hasAuthorities(final JoinPoint joinPoint, final HasEndpointAuthorities authorities) throws ApiException {
//        final SecurityContext securityContext = SecurityContextHolder.getContext();
//        if (!Objects.isNull(securityContext)) {
//            final Authentication authentication = securityContext.getAuthentication();
//            if (!Objects.isNull(authentication)) {
//                final String username = authentication.getName();
//
//                final Collection<? extends GrantedAuthority> userAuthorities = authentication.getAuthorities();
//
//                if (Stream.of(authorities.authorities()).noneMatch(authorityName -> userAuthorities.stream().anyMatch(userAuthority ->
//                        authorityName.name().equals(userAuthority.getAuthority())))) {
//
//                    log.error("User {} does not have the correct authorities required by endpoint", username);
//                    throw new ApiException(DefaultExceptionReason.FORBIDDEN.toString());
//                }
//            } else {
//                log.error("The authentication is null when checking endpoint access for user request");
//                throw new ApiException(DefaultExceptionReason.UNAUTHORIZED.toString());
//            }
//        } else {
//            log.error("The security context is null when checking endpoint access for user request");
//            throw new ApiException(DefaultExceptionReason.FORBIDDEN.toString());
//        }
//    }
//
//
//}
