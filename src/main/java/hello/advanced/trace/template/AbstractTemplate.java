package hello.advanced.trace.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    public AbstractTemplate(final LogTrace trace) {
        this.trace = trace;
    }

    public final T execute(final String message) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);

            //로직 호출
            final T result = call();

            trace.end(status);
            return result;
        } catch (final Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    protected abstract T call();
}
