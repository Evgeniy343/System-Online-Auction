package by.evgen.authenticationservice.service_api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "AUCTION-SERVICE", path = "/api/v1")
public interface AuctionServiceApi {

  @RequestMapping(value = "/basket/create", method = RequestMethod.POST)
  void createBasket(@RequestHeader("id") Long userId, @RequestHeader("role") String role);
}
