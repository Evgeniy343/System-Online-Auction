package by.evgen.productservice.controller;

import by.evgen.productservice.dto.ProductDTO;
import by.evgen.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {
    private static final String AUCTION_PRODUCT_DELETED_MESSAGE = "Auction product with id - %s has been deleted!";
    private static final String AUCTION_PRODUCT_UPDATED_MESSAGE = "Auction product with id - %s has been updated!";
    private static final String AUCTION_PRODUCT_SAVED_MESSAGE = "Auction product has been saved!";
    private final ProductService productService;

    @RequestMapping(value = "hello-product", method = RequestMethod.GET)
    public String hello() {
        return "hello-product";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProductDTO> getProductById(@PathVariable @Min(0) Long id) {
        ProductDTO productDTO = productService.findById(id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<ProductDTO>> getProducts() {
        List<ProductDTO> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Long> saveProduct(@Valid @RequestBody ProductDTO productDTO) {
        ProductDTO product = productService.save(productDTO);
        return new ResponseEntity<>(product.getId(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateProductById(
            @Valid @RequestBody ProductDTO productDTO, @PathVariable @Min(0) Long id) {
        productService.update(productDTO, id);
        return new ResponseEntity<>(String.format(AUCTION_PRODUCT_UPDATED_MESSAGE, id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteProductById(@PathVariable @Min(0) Long id) {
        productService.delete(id);
        return new ResponseEntity<>(String.format(AUCTION_PRODUCT_DELETED_MESSAGE, id), HttpStatus.OK);
    }


}
