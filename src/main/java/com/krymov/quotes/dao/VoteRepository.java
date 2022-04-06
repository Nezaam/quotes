package com.krymov.quotes.dao;

import com.krymov.quotes.model.Vote;
import com.krymov.quotes.model.VotingKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, VotingKey> {
}
