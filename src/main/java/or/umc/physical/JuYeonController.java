package or.umc.physical;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JuYeonController {

    private final JuYeonService juYeonService;

    @PostMapping("/juyeon/wake-up")
    public void wakeUpJuYeon() {

        juYeonService.wakeUp();
    }
}
