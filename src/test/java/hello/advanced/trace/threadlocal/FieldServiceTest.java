package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private final FieldService fieldService = new FieldService();

    @Test
    void field() {
        log.info("main start");
        final Runnable userA = () -> fieldService.logic("userA");
        final Runnable userB = () -> fieldService.logic("userB");

        final Thread threadA = new Thread(userA, "thread-A");
        final Thread threadB = new Thread(userB, "thread-B");

        threadA.start();
        sleep(2000); // 동시성 문제 발생 X
//        sleep(100); // 동시성 문제 발생 O
        threadB.start();

        sleep(3000); // 메인 쓰레드 종료 대기
        log.info("main exit");
    }

    private void sleep(final int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
