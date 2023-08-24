package example.naveen.controller;

import example.naveen.dto.CustomerDto;
import example.naveen.entity.Image;
import example.naveen.repository.ImageRepository;
import example.naveen.service.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "CustomerController",description = "Customer Management")
public class CustomerController {

    private final CustomerService customerService;
    private final ImageRepository imageDbRepository;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getCustomer() {
        List<CustomerDto> allCustomers = customerService.getAllCustomers();
        return ResponseEntity.ok(allCustomers);
    }

    @PostMapping("/customer")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto createdCustomer = customerService.saveCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") Long id) {
        CustomerDto customerDto = customerService.getCustomer(id);
        return ResponseEntity.ok(customerDto);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping(value = "/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<Long> uploadImage(@RequestParam("file") MultipartFile multipartImage) throws Exception {
        Image dbImage = new Image();
        dbImage.setName(multipartImage.getName());
        dbImage.setContent(multipartImage.getBytes());

        return ResponseEntity.ok(imageDbRepository.save(dbImage)
                .getId());
    }
    @GetMapping(value = "/image/{imageId}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) {
       Image image = imageDbRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

          return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
                .body(new ByteArrayResource(image.getContent()));
    }

    @PostMapping(value = "/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> getAllFiles(@RequestPart("files") MultipartFile[] files, Long num) {
        System.out.println(files.length+" "+num);
        return ResponseEntity.ok("imageList");

    }

}
