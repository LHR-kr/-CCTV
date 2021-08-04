package com.hanium.if050.config;

import com.hanium.if050.domain.video.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class VideoModelListener extends AbstractMongoEventListener<Video> {

    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Video> event) {
        event.getSource().setId(sequenceGeneratorService.generateSequence(Video.SEQUENCE_NAME));
    }
}
