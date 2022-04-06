package com.krymov.quotes.dao;

import com.krymov.quotes.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuoteRepository extends JpaRepository<Quote, Long> {

}
