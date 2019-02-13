package com.github.karynaiko.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.karynaiko.model.User;
import com.github.karynaiko.service.UserService;
import com.github.karynaiko.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;
import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

@Controller
@RequestMapping("/oauth/vk")
public class VKController {
    private static final String AUTHORIZE_URL = "https://oauth.vk.com/authorize";
    private static final String METHOD_URL = "https://api.vk.com/method/";
    private static final String CLIENT_ID = "6856264";
    private static final String CLIENT_SECRET = "jvyBTez2uOybuZB5RzRq";
    private static final String REDIRECT_URI = "http://localhost:8080/oauth/vk/callback";
    private static final String TOKEN = "token";
    private static final String VERSION = "5.92";

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
                + "&redirect_uri=" + REDIRECT_URI
                + "&response_type=" + TOKEN
                + "&v=" + VERSION;
    }

    @GetMapping("/callback")
    public ModelAndView authenticate(@RequestParam Map<String,String> allRequestParams, RedirectAttributes attr) {
        String accessToken = allRequestParams.get("access_token");
        String expire = allRequestParams.get("expires_in");
        String userId = allRequestParams.get("user_id");
        getNewUser(accessToken, userId);
        return authorizeAndRedirect(userId);
    }

    private User getNewUser(String accessToken, String userId) {
        UriComponentsBuilder builder = fromHttpUrl(METHOD_URL + "users.get")
                .queryParam("user_ids", userId)
                .queryParam("fields", "first_name,last_name")
                .queryParam("access_token", accessToken)
                .queryParam("v", VERSION);
        ResponseEntity<JsonNode> entityInfo = template.getForEntity(builder.build().encode().toUri(), JsonNode.class);
        String firstName = entityInfo.getBody().get(0).get("first_name").asText();
        String secondName = entityInfo.getBody().get(0).get("second_name").asText();
        return new User();
    }


    private ModelAndView authorizeAndRedirect(String id) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(id);
            getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
            return new ModelAndView("redirect:/friends");
        } catch (UsernameNotFoundException ex) {
            User user = new User();
            return new ModelAndView("redirect:/friends");
        }
    }
}
//http://localhost:8080/oauth/vk/callback#access_token=dab57a4e9daaa5f5c71418edb97d156b07c6f77230f4d578a1d7f60457026c25b497eb8ecf0d8557db6d6&expires_in=86400&user_id=19105970