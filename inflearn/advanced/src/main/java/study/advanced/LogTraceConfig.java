package study.advanced;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.advanced.trace.logtrace.FieldLogTrace;
import study.advanced.trace.logtrace.LogTrace;
import study.advanced.trace.logtrace.ThreadLocalLogTrace;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}
