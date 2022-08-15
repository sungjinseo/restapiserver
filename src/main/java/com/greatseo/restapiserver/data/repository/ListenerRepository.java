package com.greatseo.restapiserver.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.greatseo.restapiserver.data.entity.Listener;

public interface ListenerRepository extends JpaRepository<Listener, Long> {

}
