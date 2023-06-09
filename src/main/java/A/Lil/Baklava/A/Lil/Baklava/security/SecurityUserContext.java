package A.Lil.Baklava.A.Lil.Baklava.security;

import A.Lil.Baklava.A.Lil.Baklava.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUserContext implements UserContext {
    @Override
    public User getCurrentLoggedInUser() {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return myUserDetails.getUser();
    }
}
