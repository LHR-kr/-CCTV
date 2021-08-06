package com.hanium.if050.service.cctv;

import com.hanium.if050.domain.cctv.Cctv;
import com.hanium.if050.domain.cctv.CctvRepository;
import com.hanium.if050.web.dto.CctvSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CctvService {

    private final CctvRepository cctvRepository;

    public String cctvSave(CctvSaveRequestDto requestDto) {

        Cctv entity = requestDto.toEntity();

        cctvRepository.save(entity);

        return entity.getCctvID();
    }


}
