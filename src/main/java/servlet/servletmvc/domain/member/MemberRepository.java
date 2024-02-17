package servlet.servletmvc.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Repository로 데이터 저장소 만들기 (DB X, 메모리로 데이터 저장)
public class MemberRepository {
    private Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    private static final MemberRepository instance = new MemberRepository();    // 싱글톤

    public static MemberRepository getInstance(){   // 싱글톤 객체 반환
        return instance;
    }
    private MemberRepository(){ // 기본 생성자

    }

    public Member save(Member member){  // 데이터 저장
        member.setId(++sequence);   // 기본키(PK) 역할
        store.put(member.getId(), member);  // Map에 고유 id와 member 객체를 저장
        return member;
    }

    public Member findById(Long id){    // id로 member 검색
        return store.get(id);
    }

    public List<Member> findAll(){  // 모든 데이터 반환
        return new ArrayList<>(store.values());
    }

    public void clearStore(){   // 모든 데이터 클리어
         store.clear();
    }
}
