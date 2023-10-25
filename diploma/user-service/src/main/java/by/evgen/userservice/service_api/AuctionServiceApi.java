package by.evgen.userservice.service_api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "AUCTION-SERVICE", path = "/api/v1")
public interface AuctionServiceApi {

  @RequestMapping(value = "/basket/create", method = RequestMethod.POST)
  void createBasket(@RequestHeader("id") Long userId, @RequestHeader("role") String role);

  @RequestMapping(value = "/basket/delete/{userId}", method = RequestMethod.DELETE)
  void deleteBasket(@RequestHeader("id") Long Id, @RequestHeader("role") String role, @PathVariable Long userId);

  @RequestMapping(value = "/bids/delete/{id}", method = RequestMethod.DELETE)
  void deleteAllAuctionsByUserId(@RequestHeader("id") Long userId, @RequestHeader("role") String role, @PathVariable Long id);

  @RequestMapping(value = "/bids/delete/{id}", method = RequestMethod.DELETE)
  void deleteAllBidsByUserId(@RequestHeader("id") Long userId, @RequestHeader("role") String role, @PathVariable Long id);
}
