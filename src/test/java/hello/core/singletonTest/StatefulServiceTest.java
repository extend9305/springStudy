package hello.core.singletonTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A사용자 10000원 주문
        int userAprice = statefulService1.order("userA", 10000);

        // ThreadB : B사용자 20000원 주문
        int userBprice = statefulService2.order("userB", 20000);

        //ThreadA : 사용자A 주문 금액 조회
        //int price = statefulService1.getPrice();
        System.out.println("price = " + userAprice);


        //Assertions.assertThat(statefulService1.getPrice()).isEqualTo(statefulService2.getPrice());
        // 공유필드는 진짜 조심해야하고 스프링은 항상 무상태(Stateless)로 설계해야한다.

    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}
