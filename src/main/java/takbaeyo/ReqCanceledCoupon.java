
package takbaeyo;

public class ReqCanceledCoupon extends AbstractEvent {

    private Long id;
    private Long memberId;
    private String status;
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

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCoupon() { return coupon; }
    public void setCoupon(Long coupon) { this.coupon = coupon; }

}
