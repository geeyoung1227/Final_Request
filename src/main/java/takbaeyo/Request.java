package takbaeyo;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Entity
@Table(name="Request_table")
public class Request {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long memberId;
    private Long qty;
    private String method="";
    private String status="Registered";

    @PostPersist
    public void onPostPersistPoint(){
        Requested requested = new Requested();
        BeanUtils.copyProperties(this, requested);
        requested.publishAfterCommit();

        if("money".equals(method)){
            takbaeyo.external.Payment payment = new takbaeyo.external.Payment();
            payment.setRequestId(this.getId());
            payment.setMemberId(this.getMemberId());
            payment.setStatus("Paid");

            RequestApplication.applicationContext.getBean(takbaeyo.external.PaymentService.class)
                    .dopay(payment);
        }

        if("coupon".equals(method)){
            takbaeyo.external.Customer customer = new takbaeyo.external.Customer();
            customer.setRequestId(this.getId());
            customer.setMemberId(this.getMemberId());
            customer.setStatus("PaidCoupon");

            RequestApplication.applicationContext.getBean(takbaeyo.external.CustomerService.class)
                    .payCoupon(customer);
        }

    }

    @PreUpdate
    public void onPreUpdate(){
        if("money".equals(method)) {
            ReqCanceled reqCanceled = new ReqCanceled();
            BeanUtils.copyProperties(this, reqCanceled);
            reqCanceled.publishAfterCommit();
        }

        if("coupon".equals(method)){
            ReqCanceledCoupon reqCanceledCoupon = new ReqCanceledCoupon();
            BeanUtils.copyProperties(this, reqCanceledCoupon);
            reqCanceledCoupon.publishAfterCommit();
        }
    }


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

    public Long getQty() {
        return qty;
    }
    public void setQty(Long qty) {
        this.qty = qty;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
}
