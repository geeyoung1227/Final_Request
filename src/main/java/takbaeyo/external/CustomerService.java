
package takbaeyo.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="customer", url="${api.url.customer}")
//@FeignClient(name="customer", url="http://customer:8080")
public interface CustomerService {

    @RequestMapping(method= RequestMethod.POST, path="/customers")
    public void payCoupon(@RequestBody Customer customer);

}