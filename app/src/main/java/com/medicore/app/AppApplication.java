package com.medicore.app;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.medicore.app.models.Appointment;
import com.medicore.app.repository.AppointmentRepository;

@SpringBootApplication
@EnableScheduling
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
    CommandLineRunner testRepository(AppointmentRepository repo) {
        return args -> {
            System.out.println("=== probando conexión y métodos ===");
            
            // 1. Intenta contar las citas (debería dar 0 o las que tengas en BD)
            List<Appointment> appointments = repo.findByIsAvailable(true);
            for(Appointment appointment: appointments ){
				System.out.println(appointment.getDateTime());
			}
            
            // Aquí puedes intentar un save() rápido o un findBy...
            System.out.println("=== prueba finalizada con éxito ===");
        };
	}
}
