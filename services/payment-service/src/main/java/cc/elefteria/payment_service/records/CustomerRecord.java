package cc.elefteria.payment_service.records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated

public record CustomerRecord(
    String id,
    @NotNull(message = "Firstname is required")
    String firstName,
    @NotNull(message = "Last is required")
    String lastName,
    @NotNull(message = "Email is required")
    @Email(message = "Customer email is not valid")
    String email
) {
}
