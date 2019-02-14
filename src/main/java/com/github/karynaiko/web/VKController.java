package com.github.karynaiko.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.karynaiko.model.Role;
import com.github.karynaiko.model.User;
import com.github.karynaiko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.github.karynaiko.web.OAuthUtil.*;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;
import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

@Controller
@RequestMapping("/oauth/vk")
public class VKController {

    @Autowired
    private RestTemplate template;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;


    @GetMapping("/authorize")
    public String authorize() {
        return "redirect:" + AUTHORIZE_URL
                + "?client_id=" + CLIENT_ID
                + "&display=" + "page"
                + "&redirect_uri=" + REDIRECT_URI
                + "&scope=" + "friends"
                + "&response_type=" + "code"
                + "&v=" + VERSION;
    }

    @GetMapping("/callback")
    public ModelAndView authenticate(@RequestParam Map<String,String> allRequestParams, RedirectAttributes attr) {

        String code = allRequestParams.get("code");
        ResponseEntity<JsonNode> node = getAccessToken(code);
        String token = node.getBody().get("access_token").asText();
        String userId = node.getBody().get("user_id").asText();
        String expire = node.getBody().get("expires_in").asText();

        return authorizeAndRedirect(token, expire, userId);
    }

    private ResponseEntity<JsonNode> getAccessToken(String code) {
        UriComponentsBuilder builder = fromHttpUrl(ACCESS_TOKEN_URL)
                .queryParam("client_id", CLIENT_ID)
                .queryParam("client_secret", CLIENT_SECRET)
                .queryParam("redirect_uri", REDIRECT_URI)
                .queryParam("code", code);
        return template.postForEntity(builder.build().encode().toUri(), null, JsonNode.class);
    }

    private User getNewUser(String accessToken, String userId) {
        UriComponentsBuilder builder = fromHttpUrl(METHOD_URL + "users.get")
                .queryParam("user_ids", userId)
                .queryParam("fields", "first_name,last_name")
                .queryParam("access_token", accessToken)
                .queryParam("v", VERSION);
        ResponseEntity<JsonNode> entityInfo = template.getForEntity(builder.build().encode().toUri(), JsonNode.class);
        String firstName = entityInfo.getBody().get("response").get(0).get("first_name").asText();
        String secondName = entityInfo.getBody().get("response").get(0).get("last_name").asText();
        User user = new User();
        user.setId(Integer.parseInt(userId));
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setPassword("");
        user.setEmail("");
        return user;
    }

    private void updateOrCreate(String token, String expire, String userId) {
        User user = userService.get(userId);

        if(user == null) {
            user = getNewUser(token, userId);
            user.setToken(token);
            Set<Role> roles = new HashSet<>();
            roles.add(Role.ROLE_ADMIN);
            user.setRoles(roles);
            userService.create(user);
        } else {
            user.setToken(token);
            userService.update(user);
        }
    }

    private ModelAndView authorizeAndRedirect(String token, String expire, String userId) {
        updateOrCreate(token, expire, userId);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
        return new ModelAndView("redirect:/friends");
    }
}