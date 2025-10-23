package in.rahul.main.Service;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {

		System.out.println("Logged in user:" + authentication.getName());

		System.out.println("Authorities:" + authentication.getAuthorities());

		boolean isAdmin = authentication.getAuthorities().stream()
				.anyMatch(granted -> granted.getAuthority().equals("ROLE_ADMIN"));
		if (isAdmin) {
			response.sendRedirect("/admin");

		} else {
			response.sendRedirect("/user");

		}

		// super.onAuthenticationSuccess(request, response, authentication);
	}

}
