package com.hanium.if050.config;

import com.hanium.if050.domain.sum.Calculate;
import com.hanium.if050.domain.video.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SumModelListener extends AbstractMongoEventListener<Calculate> {

    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Calculate> event) {
        event.getSource().setId(sequenceGeneratorService.generateSequence(Calculate.SEQUENCE_NAME));
    }
}
