package dcm.job.controller;

import dcm.job.dto.JobRequest;
import dcm.job.dto.JobResponse;
import dcm.job.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/job")
@RestController
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping
    public ResponseEntity<List<JobResponse>> readJobs() {
        List<JobResponse> jobs = jobService.readJobs();
        return ResponseEntity.ok(jobs);
    }

    @PostMapping
    public ResponseEntity<Void> writeJobs(@RequestBody @Valid JobRequest jobRequest) {
        jobService.writeJobs(jobRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<Void> deleteJobs(@PathVariable Long jobId) {
        jobService.deleteJob(jobId);
        return ResponseEntity.ok().build();
    }

}
