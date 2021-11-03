package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class ThreadLocalLogTraceTest {

    private final LogTrace trace = new ThreadLocalLogTrace();

    @Test
    void begin_end_level2() {
        final TraceStatus status1 = trace.begin("hello1");
        final TraceStatus status2 = trace.begin("hello2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception_level2() {
        final TraceStatus status1 = trace.begin("hello1");
        final TraceStatus status2 = trace.begin("hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }

}
