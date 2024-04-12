package dcm.hobby.service;

import dcm.common.ServiceTest;
import com.dcm.hobby.domain.Hobby;
import com.dcm.hobby.domain.repository.HobbyRepository;
import com.dcm.hobby.dto.HobbyRequest;
import com.dcm.hobby.dto.HobbyResponse;
import com.dcm.hobby.dto.HobbyUpdateRequest;
import com.dcm.hobby.exception.NotFoundHobbyException;
import com.dcm.hobbydetail.domain.HobbyDetail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static com.dcm.global.enumurate.YN.Y;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class HobbyServiceTest extends ServiceTest {

    @Mock
    private HobbyRepository hobbyRepository;

    @InjectMocks
    private HobbyService hobbyService;

    @DisplayName("성공적으로 관심사 목록을 조회한다.")
    @Test
    void successReadHobby() {
        // given
        Hobby hobby1 = Hobby.of(1L, "운동/스포츠", Y);
        Hobby hobby2 = Hobby.of(1L, "아웃도어여행", Y);

        List<HobbyDetail> mockHobby1DetailList = List.of(HobbyDetail.of(1L, "자전거", Y, hobby1));
        List<HobbyDetail> mockHobby2DetailList = List.of(HobbyDetail.of(1L, "등산", Y, hobby2));

        List<Hobby> mockHobbyList = List.of(
                new Hobby(1L, "운동/스포츠", Y, mockHobby1DetailList),
                new Hobby(1L, "아웃도어여행", Y, mockHobby2DetailList)
        );

        // when
        when(hobbyRepository.findAll()).thenReturn(mockHobbyList);
        List<HobbyResponse> hobbyResponses = hobbyService.readHobbyList();

        // then
        assertThat(hobbyResponses).isNotEmpty();
        assertEquals(mockHobbyList.size(), hobbyResponses.size());
    }

    @DisplayName("성공적으로 관심사 항목을 등록한다.")
    @Test
    void successWriteHobby() {
        // given
        HobbyRequest request = new HobbyRequest("운동/스포츠", Y);
        Hobby createHobby = Hobby.of(1L, "운동/스포츠", Y);

        // when
        doReturn(createHobby).when(hobbyRepository).save(any(Hobby.class));
        hobbyService.writeHobby(request);

        // then
        verify(hobbyRepository).save(any(Hobby.class));
    }

    @DisplayName("성공적으로 관심사 항목을 수정한다.")
    @Test
    void successUpdateHobby() {
        // given
        HobbyUpdateRequest request = new HobbyUpdateRequest(1L, "아웃도어여행", Y);
        Hobby updateHobby = Hobby.of(1L, "아웃도어여행", Y);

        // when
        doReturn(true).when(hobbyRepository).existsById(request.hobbyId());
        doReturn(updateHobby).when(hobbyRepository).save(any(Hobby.class));
        hobbyService.updateHobby(request);

        // then
        verify(hobbyRepository).existsById(request.hobbyId());
        verify(hobbyRepository).save(any(Hobby.class));
    }

    @DisplayName("성공적으로 관심사 항목을 삭제한다.")
    @Test
    void successDeleteHobby() {
        // given
        Long hobbyId = 1L;

        // when
        doReturn(true).when(hobbyRepository).existsById(hobbyId);
        doNothing().when(hobbyRepository).deleteById(hobbyId);
        hobbyService.deleteHobby(hobbyId);

        // then
        verify(hobbyRepository).existsById(hobbyId);
        verify(hobbyRepository).deleteById(hobbyId);
    }

    @DisplayName("등록되지 않은 관심사 항목을 삭제할 때 예외를 발생한다.")
    @Test
    void notFoundHobby() {
        // given
        Long hobbyId = 1L;

        // when
        doReturn(false).when(hobbyRepository).existsById(hobbyId);
        doNothing().when(hobbyRepository).deleteById(hobbyId);

        // then
        assertThrows(NotFoundHobbyException.class, () -> hobbyService.deleteHobby(hobbyId));
        verify(hobbyRepository).existsById(hobbyId);
        verify(hobbyRepository, times(0)).deleteById(hobbyId);
    }

}