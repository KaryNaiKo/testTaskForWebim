package com.github.karynaiko.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.karynaiko.model.User;
import com.github.karynaiko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(value = "/ajax/friends", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonNode> getFriends() {
        int userId = SecurityUtil.authUserId();
        User user = userService.get(Integer.toString(userId));
        UriComponentsBuilder builder = fromHttpUrl(METHOD_URL + "friends.search")
                .queryParam("count", "5")
                .queryParam("fields", "photo_200")
                .queryParam("access_token", user.getToken())
                .queryParam("v", VERSION);

        ResponseEntity node = template.getForEntity(builder.build().encode().toUri(), JsonNode.class);
        JsonNode json = (JsonNode) node.getBody();

        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}
