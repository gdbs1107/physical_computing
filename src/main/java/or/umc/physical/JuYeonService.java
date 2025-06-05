package or.umc.physical;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JuYeonService {

    private final JuYeonRepository juYeonRepository;

    @Transactional
    public void wakeUp(){
        JuYeon juyeon = juYeonRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("주연이를 찾을 수가 없어요"));

        juyeon.addAlarmCount();
        juYeonRepository.save(juyeon);

        if((juyeon.getAlarmCount() % 5) ==0){
            wakeUpMail();
        }
    }

    private void wakeUpMail(){
        throw new IllegalArgumentException("주연이한테 일어나라고 메일 보내기");
    }

}
