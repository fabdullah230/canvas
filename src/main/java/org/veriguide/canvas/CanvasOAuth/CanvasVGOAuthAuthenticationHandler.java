package org.veriguide.canvas.CanvasOAuth;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth.provider.ConsumerAuthentication;
import org.springframework.security.oauth.provider.ConsumerCredentials;
import org.springframework.security.oauth.provider.OAuthAuthenticationHandler;
import org.springframework.security.oauth.provider.token.OAuthAccessProviderToken;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;

@Component
public class CanvasVGOAuthAuthenticationHandler implements OAuthAuthenticationHandler {


    final static Logger log = LoggerFactory.getLogger(CanvasVGOAuthAuthenticationHandler.class);

    public static SimpleGrantedAuthority user = new SimpleGrantedAuthority("ROLE_USER");
    public static SimpleGrantedAuthority admin = new SimpleGrantedAuthority("ROLE_ADMIN");
    public static SimpleGrantedAuthority oauth = new SimpleGrantedAuthority("ROLE_OAUTH");



    //creating authentication
    @Override
    public Authentication createAuthentication(HttpServletRequest request, ConsumerAuthentication authentication, OAuthAccessProviderToken authToken) {
        Collection<GrantedAuthority> authorities = new HashSet<>(authentication.getAuthorities());
        // attempt to create a user Authority
        String username = request.getParameter("username");

        //adding authorities, need to be modified for production build
            authorities.add(user);
            authorities.add(admin);
            authorities.add(oauth);


        Principal principal = new NamedOAuthPrincipal(username, authorities,
                authentication.getConsumerCredentials().getConsumerKey(),
                authentication.getConsumerCredentials().getSignature(),
                authentication.getConsumerCredentials().getSignatureMethod(),
                authentication.getConsumerCredentials().getSignatureBaseString(),
                authentication.getConsumerCredentials().getToken()
        );
        Authentication auth = new UsernamePasswordAuthenticationToken(principal, null, authorities);
        log.info("createAuthentication generated auth principal (" + principal + "): req=" + request);
        return auth;
    }

    //custom principal implementation
    public static class NamedOAuthPrincipal extends ConsumerCredentials implements Principal {
        public String name;
        public Collection<GrantedAuthority> authorities;

        public NamedOAuthPrincipal(String name, Collection<GrantedAuthority> authorities, String consumerKey, String signature, String signatureMethod, String signatureBaseString, String token) {
            super(consumerKey, signature, signatureMethod, signatureBaseString, token);
            this.name = name;
            this.authorities = authorities;
        }

        @Override
        public String getName() {
            return name;
        }

        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public String toString() {
            return "NamedOAuthPrincipal{" +
                    "name='" + name + '\'' +
                    ", key='" + getConsumerKey() + '\'' +
                    ", base='" + getSignatureBaseString() + '\'' +
                    "}";
        }
    }
}
