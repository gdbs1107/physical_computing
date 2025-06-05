package or.umc.physical.juyeon;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JuYeonController {

    private final JuYeonService juYeonService;

    @PostMapping("/juyeon/wake-up")
    public void wakeUpJuYeon() throws MessagingException {

        juYeonService.wakeUp();
    }
}
