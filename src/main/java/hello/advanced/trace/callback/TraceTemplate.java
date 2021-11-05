package hello.advanced.trace.callback;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

public class TraceTemplate {

    private final LogTrace trace;

    public TraceTemplate(final LogTrace trace) {
        this.trace = trace;
    }

    public <T> T execute(final String message, final TraceCallback<T> callback) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);

            //로직 호출
            final T result = callback.call();

            trace.end(status);
            return result;
        } catch (final Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
