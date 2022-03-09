package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDicountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final  MemberRepository memberRepository;
    //private final DiscountPolicy discountPolicy = new FixDicountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final  DiscountPolicy discountPolicy;

//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }
//      RequiredArgsConstructor-> 이 생성자 역할을 한다.

//    @Autowired // 1.이렇게 빈이 2개 중복시 타입으로 먼저 찾은후 다음 객체 명으로 찾는다
//    public OrderServiceImpl(MemberRepository memberRepository,@Qualifier("mainDiscountPolicy") DiscountPolicy rateDiscountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = rateDiscountPolicy;
//    }
//    @Autowired // 2. @Qualifier 을줘 무슨 빈을 사용할지 명시.
//    public OrderServiceImpl(MemberRepository memberRepository,@Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }
//    @Autowired // 3.RateDiscountPolicy에 @Primary 선언 -> 자동적으로 먼저  RateDiscountPolicy을 선택
//    public OrderServiceImpl(MemberRepository memberRepository,DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }
    //Qualifier 가 Primary 보다 우선순위가 높다.

    @Autowired //Annotation 직접 만들어서 사용.( 컴파일시 문자는 오류를 잡을수없기 때문에 이런식으로 운용)
    public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    // 프라이머리로 해결 가능하다면  Primary로


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member =memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
