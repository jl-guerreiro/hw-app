package com.hwapp.servers.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hwapp.servers.model.BareMetal;
import com.hwapp.servers.model.VirtualMachine;

@Repository
public interface VirtualMachineRepository extends CrudRepository<VirtualMachine, String> {

	ArrayList<VirtualMachine> findByBareMetal(BareMetal bareMetal);
	
}
