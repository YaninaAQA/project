package dto;

public class OrderDto {
    private String status;
    private int courierId;
    private String customerName;
    private String customerPhone;
    private String comment;
    private long id;

    public OrderDto(String customerName, String customerPhone, String comment) {
        this.status = "OPEN";
        this.courierId = 0;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.comment = comment;
        this.id = 1;
    }
}
