package com.mohkh.book;

import com.mohkh.book.user.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class ApplicationAuditAware implements AuditorAware<Integer> {


    @Override
    public Optional<Integer> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // reterive the current Authentication object the object contains details about the current authenticated user o r if no user is authenticated
        if(authentication == null ||
            !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();

            // check if is authenticated or not
        }

        User userPrincipal = (User) authentication.getPrincipal();
        // cast the principle to the user class and reterives the its id using userPrinciple.getiD()
        return Optional.ofNullable(userPrincipal.getId());
        // return an empty optional instead of causing a nullpointerexception
    }
}

/*

ApplicationAuditAware captures the authenticated user's ID and passes it to Spring Data’s auditing mechanism, making it easy to automatically track which user created or modified an entity. This is particularly useful for maintaining an audit trail in your database, especially in applications that require tracking user actions for security, compliance, or history purposes.
 */
