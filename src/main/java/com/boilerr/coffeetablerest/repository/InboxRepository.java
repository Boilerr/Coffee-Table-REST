package com.boilerr.coffeetablerest.repository;

import com.boilerr.coffeetablerest.model.Inbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InboxRepository extends JpaRepository<Inbox, Long>{
}
