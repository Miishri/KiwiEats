package org.delivery.kiwieats.boostrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    @Transactional
    @Override
    public void run(String... args) throws Exception {

    }

    private void loadTestData() {


    }
}
