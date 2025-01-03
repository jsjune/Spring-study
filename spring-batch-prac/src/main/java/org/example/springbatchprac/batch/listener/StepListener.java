package org.example.springbatchprac.batch.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StepListener implements StepExecutionListener, ChunkListener {

    @Override
    public void beforeChunk(ChunkContext context) {
        log.info("before chunk - step execution: {}", context.getStepContext().getStepExecution());
        ChunkListener.super.beforeChunk(context);
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("before step - step execution: {}", stepExecution);
        StepExecutionListener.super.beforeStep(stepExecution);
    }
}
