package com.hanium.if050.service.cctv;

import com.hanium.if050.domain.cctv.Cctv;
import com.hanium.if050.domain.cctv.CctvRepository;
import com.hanium.if050.web.dto.CctvSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CctvService {

    private final CctvRepository cctvRepository;

    @Transactional
    public String cctvSave(CctvSaveRequestDto requestDto) {

        Cctv entity = requestDto.toEntity();

        cctvRepository.save(entity);

        return entity.getCctvID();
    }

    @Transactional
    public String getUrl(String cctvId) {
        Optional<Cctv> entity = cctvRepository.findById(cctvId);

        if (entity.isPresent()) {
            return entity.get().getCctvUrl();
        } else {
            return "";
        }
    }


}
