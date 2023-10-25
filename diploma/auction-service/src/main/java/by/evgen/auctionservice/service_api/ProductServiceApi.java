package by.evgen.auctionservice.service_api;

import by.evgen.auctionservice.dto.ProductDTO;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "PRODUCT-SERVICE", path = "/api/v1")
public interface ProductServiceApi {
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  ResponseEntity<ProductDTO> getProductById(@RequestHeader("id") Long userId, @RequestHeader("role") String role, @PathVariable @Min(0) Long id);

  @RequestMapping(value = "", method = RequestMethod.GET)
  ResponseEntity<List<ProductDTO>> getProducts(@RequestHeader("id") Long userId, @RequestHeader("role") String role);

  @RequestMapping(value = "", method = RequestMethod.POST)
  ResponseEntity<Long> saveProduct(@RequestHeader("id") Long userId, @RequestHeader("role") String role, @Valid @RequestBody ProductDTO productDTO);

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  ResponseEntity<String> updateProductById(@RequestHeader("id") Long userId, @RequestHeader("role") String role,
      @Valid @RequestBody ProductDTO productDTO, @PathVariable @Min(0) Long id);

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  ResponseEntity<String> deleteProductById(@RequestHeader("id") Long userId, @RequestHeader("role") String role, @PathVariable @Min(0) Long id);
}
