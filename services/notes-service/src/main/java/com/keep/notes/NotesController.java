package com.keep.notes;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class NotesController {

    @GetMapping("/api/notes/hello")
    public String hello() {
        return "Hello from Notes service";
    }

}
