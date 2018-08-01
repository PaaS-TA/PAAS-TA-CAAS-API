package org.paasta.caas.api.temp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController {

    @RequestMapping
    String index() {
        return "TEMPORARY INDEX :: 임시 페이지";
    }
}
