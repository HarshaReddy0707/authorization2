package authorization.authorization;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.LockedException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private  LoginAttemptService loginattempts;


    public CustomAuthenticationFailureHandler( LoginAttemptService loginattempts) {
    this.loginattempts = loginattempts;
}
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        
       String username = request.getParameter("username");
        loginattempts.loginFailed(username);
        response.sendRedirect("/access-denied");
    }
}