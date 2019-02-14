package com.github.karynaiko.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.karynaiko.model.User;
import com.github.karynaiko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;
import static com.github.karynaiko.web.OAuthUtil.*;

@RestController
@RequestMapping()
public class AjaxController {
    @Autowired
    private RestTemplate template;

    @Autowired
    private UserService userService;

    @GetMapping("/ajax/friends/{userId}")
    public ResponseEntity<JsonNode> getFriends(@PathVariable("userId") String userId) {
        User user = userService.get(userId);
        UriComponentsBuilder builder = fromHttpUrl(METHOD_URL + "users.get")
                .queryParam("user_ids", userId)
                .queryParam("fields", "first_name,last_name")
                .queryParam("access_token", user.getToken())
                .queryParam("v", VERSION);
        return template.getForEntity(builder.build().encode().toUri(), JsonNode.class);
    }
}
