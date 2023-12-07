package br.com.delivery.service;

import br.com.delivery.entity.Logstore_standard_log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@ApplicationScoped
public class Logstore_standard_logService {

    public void create(Logstore_standard_log logstore_standard_log) {
        logstore_standard_log.persist();
    }

}
