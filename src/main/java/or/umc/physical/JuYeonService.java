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
                .orElseThrow(() -> new RuntimeException("JuYeon not found"));

        juyeon.addAlarmCount();
        juYeonRepository.save(juyeon);
    }

}
