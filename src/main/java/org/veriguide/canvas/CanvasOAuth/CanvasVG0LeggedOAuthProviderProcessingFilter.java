package org.veriguide.canvas.CanvasOAuth;

import org.springframework.security.oauth.provider.OAuthAuthenticationHandler;
import org.springframework.security.oauth.provider.OAuthProcessingFilterEntryPoint;
import org.springframework.security.oauth.provider.filter.ProtectedResourceProcessingFilter;
import org.springframework.security.oauth.provider.token.OAuthProviderTokenServices;

public class CanvasVG0LeggedOAuthProviderProcessingFilter extends ProtectedResourceProcessingFilter {

    public CanvasVG0LeggedOAuthProviderProcessingFilter(CanvasVGConsumerDetailsService oAuthConsumerDetailsService, CanvasVGOAuthNonceServices oAuthNonceServices, OAuthProcessingFilterEntryPoint oAuthProcessingFilterEntryPoint, OAuthAuthenticationHandler oAuthAuthenticationHandler, OAuthProviderTokenServices oAuthProviderTokenServices, boolean testing) {
        super();
        setAuthenticationEntryPoint(oAuthProcessingFilterEntryPoint);
        setAuthHandler(oAuthAuthenticationHandler);
        setConsumerDetailsService(oAuthConsumerDetailsService);
        setNonceServices(oAuthNonceServices);
        setTokenServices(oAuthProviderTokenServices);
        if (testing) {
            setIgnoreMissingCredentials(true); // die if OAuth params are not included
        }
    }

}
