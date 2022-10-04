package study.junit5prac.study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import study.junit5prac.domain.Member;
import study.junit5prac.domain.Study;
import study.junit5prac.domain.StudyStatus;
import study.junit5prac.member.MemberService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {
    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

//    @Test
//    void createStudyService() {
////        MemberService memberService = mock(MemberService.class);
////        StudyRepository studyRepository = mock(StudyRepository.class);
//        StudyService studyService = new StudyService(memberService, studyRepository);
//        assertNotNull(studyService);
//    }

    @Test
    void createStudyServiceTest(@Mock MemberService memberService,
                                @Mock StudyRepository studyRepository) {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Optional<Member> optional = memberService.findById(100L);
        assertThat(optional).isEmpty();

        Member member = new Member();
        member.setId(1L);
        member.setEmail("avraskio@gmail.com");

        when(memberService.findById(1L)).thenReturn(Optional.of(member));
//        when(memberService.findById(any())).thenReturn(Optional.of(member));

        Study study = new Study(10, "java");

        Optional<Member> findMember = memberService.findById(1L);
        assertThat(findMember.get().getEmail()).isEqualTo("avraskio@gmail.com");

        doThrow(new IllegalArgumentException()).when(memberService).validate(100L);

        assertThrows(IllegalArgumentException.class, () -> {
            memberService.validate(100L);
        });

//        memberService.validate(100L);

        studyService.createNewStudy(1L, study);

    }

    @Test
    void createNewStudy(@Mock MemberService memberService,
                        @Mock StudyRepository studyRepository) {

        Member member = new Member();
        member.setId(1L);
        member.setEmail("avraskio@gmail.com");

        when(memberService.findById(any()))
                .thenReturn(Optional.of(member))
                .thenThrow(new RuntimeException())
                .thenReturn(Optional.empty());

        Optional<Member> byId = memberService.findById(1L);
        assertEquals("avraskio@gmail.com", byId.get().getEmail());

        assertThrows(RuntimeException.class, () -> {
            memberService.findById(1L);
        });

        assertEquals(Optional.empty(), memberService.findById(1L));
    }

    @Test
    void stubbingTest() {
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("avraskio@gmail.com");

        Study study = new Study(10, "테스트");

//        when(memberService.findById(1L)).thenReturn(Optional.of(member));
//        when(studyRepository.save(study)).thenReturn(study);

        given(memberService.findById(1L)).willReturn(Optional.of(member));
        given(studyRepository.save(study)).willReturn(study);

        // When
        studyService.createNewStudy(1L, study);
        System.out.println(studyService.createNewStudy(1L, study).getName());

        // Then
        assertEquals(member.getId(), study.getOwnerId());
//        verify(memberService, times(1)).notify(study);
        then(memberService).should(times(2)).notify(study);
//        verifyNoMoreInteractions(memberService);
        then(memberService).shouldHaveNoMoreInteractions();

//        verify(memberService, times(1)).notify(member);
//        verify(memberService, never()).validate(any());
//
//        InOrder inOrder = inOrder(memberService);
//        inOrder.verify(memberService).notify(study);
//        inOrder.verify(memberService).notify(member);
    }

    @DisplayName("다른 사용자가 볼 수 있도록 스터디를 공개한다.")
    @Test
    void openStudy() {
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        Study study = new Study(10, "더 자바, 테스트");
        assertNull(study.getOpenedDateTime());
        // TODO studyRepository Mock 객체의 save 메소드를호출 시 study를 리턴하도록 만들기.
        given(studyRepository.save(study)).willReturn(study);

        // When
        studyService.openStudy(study);

        // Then
        // TODO study의 status가 OPENED로 변경됐는지 확인
        assertEquals(StudyStatus.OPENED, study.getStatus());

        // TODO study의 openedDataTime이 null이 아닌지 확인
        assertNotNull(study.getOpenedDateTime());

        // TODO memberService의 notify(study)가 호출 됐는지 확인.
        then(memberService).should().notify(study);
    }


}