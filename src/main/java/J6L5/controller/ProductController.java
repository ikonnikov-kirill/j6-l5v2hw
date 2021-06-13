package J6L5.controller;

import J6L5.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import J6L5.model.Product;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
@Slf4j
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public String showProducts(Model model) {
        model.addAttribute("products", repository.index());
        log.info("/products");
        return "index";
    }

    @GetMapping("/{id}")
    public String showProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", repository.getById(id));
        log.info(":/id: " + id);
        return "show";
    }

    @GetMapping("/new")
    public String addProduct(@ModelAttribute("product") Product product) {
        log.info("/new");
        return "new";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("product", repository.getById(id));
        log.info("/" + id + "/edit");
        return "edit";
    }

    @PostMapping()
    public String create(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("Binding result has errors.");
            return "new";
        }
        repository.save(product);
        log.info("Product with id = " + product.getId() + " was saved.");
        return "redirect:/products";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            log.info("Binding result has errors.");
            return "edit";
        }
        product.setId(id);
        repository.update(product);
        log.info("Product with id = " + id + " was updated.");
        return "redirect:/products";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        if (repository.delete(id)) {
            log.info("Product with id = " + id + " was deleted.");
        } else {
            log.warn("Product with id = " + id + " was not deleted.");
        }
        return "redirect:/products";
    }
}
