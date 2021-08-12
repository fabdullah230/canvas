package org.veriguide.canvas.CanvasOAuth;

import org.springframework.security.oauth.provider.nonce.InMemoryNonceServices;
import org.springframework.stereotype.Service;

@Service
public class CanvasVGOAuthNonceServices extends InMemoryNonceServices {
    @Override
    public long getValidityWindowSeconds() {
        return 1200;
    }
}
