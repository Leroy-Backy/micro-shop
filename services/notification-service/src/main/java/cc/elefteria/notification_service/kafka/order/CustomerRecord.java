package cc.elefteria.notification_service.kafka.order;

public record CustomerRecord(
    String id,
    String firstName,
    String lastName,
    String email
) {
}
