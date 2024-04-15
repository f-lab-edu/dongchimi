package dcm.hobby.repository;

import dcm.common.RepositoryTest;
import dcm.hobby.domain.Hobby;
import dcm.hobby.domain.repository.HobbyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static dcm.global.enumurate.YN.N;
import static dcm.global.enumurate.YN.Y;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HobbyRepositoryTest extends RepositoryTest {

    @Autowired
    private HobbyRepository hobbyRepository;

    @DisplayName("성공적으로 관심사 목록을 조회한다.")
    @Test
    void successReadHobby() {
        // given
        List<Hobby> hobbyList = List.of(
                Hobby.of(1L, "운동/스포츠", Y),
                Hobby.of(2L, "아웃도어여행", Y)
        );
        hobbyRepository.saveAll(hobbyList);

        // when
        List<Hobby> hobby = hobbyRepository.findAll();

        // then
        assertThat(hobby).isNotEmpty();
        assertThat(hobby).hasSizeGreaterThan(0);
    }

    @DisplayName("성공적으로 관심사 항목을 등록한다.")
    @Test
    void successWriteHobby() {
        // given
        Hobby hobby = Hobby.of("운동/스포츠", Y);

        // when
        hobbyRepository.save(hobby);

        // then
        assertThat(hobby.getHobbyId()).isGreaterThan(0);
        assertThat(hobby.getHobbyId()).isNotNull();
    }

    @DisplayName("성공적으로 관심사 항목을 업데이트 한다.")
    @Test
    void successUpdateHobby() {
        // given
        Hobby updateHobby = Hobby.of(1L, "운동/스포츠", N);

        // when
        Hobby newHobby = hobbyRepository.save(updateHobby);

        // then
        assertEquals(N, newHobby.getUseYn());
    }

    @DisplayName("성공적으로 관심사 항목을 삭제한다.")
    @Test
    void successDeleteHobby() {
        // given
        List<Hobby> hobbyList = List.of(
                Hobby.of("운동/스포츠", Y),
                Hobby.of("아웃도어여행", Y)
        );
        hobbyRepository.saveAll(hobbyList);
        Long deleteHobbyId = hobbyList.get(0).getHobbyId();

        // when
        boolean exist = hobbyRepository.existsById(deleteHobbyId);
        hobbyRepository.deleteById(deleteHobbyId);
        boolean empty = hobbyRepository.existsById(deleteHobbyId);

        // then
        assertThat(exist).isTrue();
        assertThat(empty).isFalse();
    }

}
