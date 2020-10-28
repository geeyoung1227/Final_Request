package takbaeyo;

public class RequestedCoupon extends AbstractEvent {

    private Long id;
    private Long memberId;
    private Long status;
    private Long coupon;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getStatus() {
        return status;
    }
    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getCoupon() { return coupon; }
    public void setCoupon(Long coupon) { this.coupon = coupon; }

}
