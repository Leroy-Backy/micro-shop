package cc.elefteria.order_service.record;

public record CustomerRecord(
    String id,
    String firstName,
    String lastName,
    String email
) {
}
