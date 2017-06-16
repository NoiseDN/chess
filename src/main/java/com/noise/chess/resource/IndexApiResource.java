package com.noise.chess.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api", produces = "application/json")
public class IndexApiResource {
    @RequestMapping
    public ResponseEntity index() {
        return ResponseEntity.ok("Index API endpoint. Nothing to do here.");
    }
}
