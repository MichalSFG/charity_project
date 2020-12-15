package pl.coderslab.charity.category;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping("/addCat")
    @ResponseBody
    private String addCategory() {
        Category category = new Category();
        category.setName("other");
        categoryService.add(category);
        return "cat added";
    }
}
