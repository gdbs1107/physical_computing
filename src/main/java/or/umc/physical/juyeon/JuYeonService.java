package or.umc.physical.juyeon;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import or.umc.physical.mail.MailUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JuYeonService {

    private final JuYeonRepository juYeonRepository;
    private final MailUtil mailUtil;

    @Transactional
    public void wakeUp() throws MessagingException {
        JuYeon juyeon = juYeonRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("주연이를 찾을 수가 없어요"));

        juyeon.addAlarmCount();
        juYeonRepository.save(juyeon);

        if((juyeon.getAlarmCount() % 5) ==0){
            wakeUpMail();
        }
    }

    @Transactional
    public void newJuYeon() {
        JuYeon newJuyeon = JuYeon.builder()
                .alarmCount(0)
                .build();

        juYeonRepository.save(newJuyeon);
    }

    private void wakeUpMail() throws MessagingException {
        for (int i = 0; i < 6; i++) {
            mailUtil.sendEmail();
        }
    }

}


