package org.veriguide.canvas.CanvasOAuth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth.common.OAuthException;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.provider.BaseConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CanvasVGConsumerDetailsService implements ConsumerDetailsService {

    final static Logger log = LoggerFactory.getLogger(CanvasVGConsumerDetailsService.class);

    @Override
    public ConsumerDetails loadConsumerByConsumerKey(String consumerKey) throws OAuthException {
        BaseConsumerDetails cd;

            cd = new BaseConsumerDetails();
            cd.setConsumerKey(consumerKey);
            cd.setSignatureSecret(new SharedConsumerSecretImpl("secret_key_123")); //should be changed and imported from application properties
            cd.setConsumerName("Sample");
            cd.setRequiredToObtainAuthenticatedToken(false); // no token required (0-legged)
            log.info("OAuth check SUCCESS, consumer key: " + consumerKey);

        return cd;
    }
}
