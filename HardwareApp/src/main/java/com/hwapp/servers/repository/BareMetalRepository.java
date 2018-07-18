package com.hwapp.servers.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hwapp.servers.model.BareMetal;

@Repository
public interface BareMetalRepository extends CrudRepository<BareMetal, String> {

}
