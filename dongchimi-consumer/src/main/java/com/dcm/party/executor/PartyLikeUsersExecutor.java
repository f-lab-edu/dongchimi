package com.dcm.party.executor;

import com.dcm.message.dto.PartyLikePublishRequest;
import com.dcm.party.context.PartyLikeUsersContext;
import com.dcm.party.domain.PartyLikeUsers;
import com.dcm.party.domain.repository.PartyLikeUsersRepository;
import com.dcm.party.domain.repository.PartyRepository;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PartyLikeUsersExecutor {

    private final PartyLikeUsersContext partyLikeUsersContext;
    private final PartyLikeUsersRepository partyLikeUsersRepository;
    private final PartyRepository partyRepository;
    private ScheduledExecutorService executorService;

    public PartyLikeUsersExecutor(PartyLikeUsersContext partyLikeUsersContext,
                                PartyLikeUsersRepository partyLikeUsersRepository,
                                PartyRepository partyRepository
    ) {
        this.partyLikeUsersContext = partyLikeUsersContext;
        this.partyLikeUsersRepository = partyLikeUsersRepository;
        this.partyRepository = partyRepository;
        this.executorService = Executors.newScheduledThreadPool(1);
        this.executorService.scheduleAtFixedRate(this::executePartyLikeUsers, 0, 10, TimeUnit.SECONDS);
    }

    public void executePartyLikeUsers() {
        List<PartyLikePublishRequest> elements = partyLikeUsersContext.getElements();
        List<PartyLikeUsers> partyLikeUsers = elements.stream()
            .map(r -> PartyLikePublishRequest.toPartyLikeUsers(r.partyId(), r.memberId()))
            .collect(Collectors.toList());
        partyLikeUsersRepository.saveAll(partyLikeUsers);
    }

}
