package cc.elefteria.customer_service.record;

import cc.elefteria.customer_service.entity.Address;
import cc.elefteria.customer_service.entity.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang.StringUtils;

public record CustomerRecord(
    String id,
    @NotNull(message = "Customer firstname is required")
    String firstName,
    @NotNull(message = "Customer lastname is required")
    String lastName,
    @NotNull(message = "Customer email is required")
    @Email(message = "Customer email is not a valid email address")
    String email,
    Address address
) {
    
    public void mergeWithCustomer(Customer customer) {
        if (StringUtils.isNotBlank(firstName)) {
            customer.setFirstName(firstName);
        }
        if (StringUtils.isNotBlank(lastName)) {
            customer.setLastName(lastName);
        }
        if (StringUtils.isNotBlank(email)) {
            customer.setEmail(email);
        }
        if (address != null) {
            customer.setAddress(address);
        }
    }
    
    public static CustomerRecord fromCustomer(Customer customer) {
        return new CustomerRecord(
            customer.getId(),
            customer.getFirstName(),
            customer.getLastName(),
            customer.getEmail(),
            customer.getAddress()
        );
    }
}
