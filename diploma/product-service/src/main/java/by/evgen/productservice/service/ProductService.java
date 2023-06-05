package by.evgen.productservice.service;

import by.evgen.productservice.dto.PhotoDTO;
import by.evgen.productservice.dto.ProductDTO;
import by.evgen.productservice.exception.ProductNotFoundException;
import by.evgen.productservice.mapper.ProductMapper;
import by.evgen.productservice.model.Photo;
import by.evgen.productservice.model.Product;
import by.evgen.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private static final String PRODUCT_NOT_FOUND_MESSAGE = "Auction product with id - %s is not found!";
    private static final String PRODUCTS_ARE_OUT_OF_STOCK_MESSAGE = "Products are out of stock!";
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND_MESSAGE, id)));
        return productMapper.convertProductToProductDTO(product);
    }

    @Transactional
    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = products.stream()
                .map(productMapper::convertProductToProductDTO)
                .toList();
        if (products.isEmpty()) {
            throw new ProductNotFoundException(PRODUCTS_ARE_OUT_OF_STOCK_MESSAGE);
        } else {
            return productDTOS;
        }
    }

    @Transactional
    public ProductDTO save(ProductDTO productDTO) {
        Product product = productMapper.convertProductDTOToProduct(productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.convertProductToProductDTO(savedProduct);
    }

    @Transactional
    public ProductDTO update(ProductDTO productDTO, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND_MESSAGE, id)));
        setPhotosWithId(productDTO.getPhotos(), product.getPhotos());
        productMapper.updateExisting(productDTO, product);
        Product updatedProduct = productRepository.save(product);
        return productMapper.convertProductToProductDTO(updatedProduct);
    }

    @Transactional
    public void delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND_MESSAGE, id)));
        productRepository.deleteById(id);
    }

    private void setPhotosWithId(List<PhotoDTO> photoDTOS, List<Photo> photos) {
        for (PhotoDTO photoDTO : photoDTOS) {
            for (Photo photo : photos) {
                if (photoDTO.getName().equals(photo.getName())){
                    photoDTO.setId(photo.getId());
                }
            }
        }
    }
}
