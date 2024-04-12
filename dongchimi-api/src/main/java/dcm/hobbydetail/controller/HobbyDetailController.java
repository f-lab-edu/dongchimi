package dcm.hobbydetail.controller;

import dcm.hobbydetail.dto.HobbyDetailRequest;
import dcm.hobbydetail.dto.HobbyDetailResponse;
import dcm.hobbydetail.dto.HobbyDetailUpdateRequest;
import dcm.hobbydetail.service.HobbyDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hobby-detail")
public class HobbyDetailController {

    private final HobbyDetailService hobbyDetailService;

    @GetMapping
    public ResponseEntity<List<HobbyDetailResponse>> readHobbyDetailList() {
        List<HobbyDetailResponse> hobbyDetailList = hobbyDetailService.readHobbyDetailList();
        return ResponseEntity.ok(hobbyDetailList);
    }

    @PostMapping
    public ResponseEntity<Void> writeHobbyDetail(@RequestBody @Valid HobbyDetailRequest request) {
        hobbyDetailService.writeHobbyDetail(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping
    public void updateHobbyDetail(@RequestBody HobbyDetailUpdateRequest request) {
        hobbyDetailService.updateHobbyDetail(request);
    }

    @DeleteMapping("/{hobbyDetailId}")
    public void deleteHobbyDetail(@PathVariable Long hobbyDetailId) {
        hobbyDetailService.deleteHobbyDetail(hobbyDetailId);
    }


}
