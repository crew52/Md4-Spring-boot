package codegym.c10.com.controller;

import codegym.c10.com.dto.ITypeDTO;
import codegym.c10.com.exception.NotFountException;
import codegym.c10.com.model.Computer;
import codegym.c10.com.model.Type;
import codegym.c10.com.service.IComputerService;
import codegym.c10.com.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/types")
public class TypeController {
    @Autowired
    private IComputerService computerService;

    @Autowired
    private ITypeService typeService;

    @GetMapping
    public ModelAndView listProvince() {
        ModelAndView modelAndView = new ModelAndView("/type/index");
        Iterable<Type> types = typeService.findAll();
        modelAndView.addObject("types", types);
        return modelAndView;
    }


    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/type/create");
        modelAndView.addObject("type", new Type());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("type") Type type,
                         RedirectAttributes redirectAttributes) {
        typeService.save(type);
        redirectAttributes.addFlashAttribute("message", "Create new type successfully");
        return "redirect:/types";
    }

    @GetMapping("/view-type/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id) throws NotFountException {
        Optional<Type> typeOptional = typeService.findById(id);

        Iterable<Computer> computers = computerService.findAllByType(typeOptional.get());

        ModelAndView modelAndView = new ModelAndView("/computer/index");
        modelAndView.addObject("computers", computers);
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) throws NotFountException {
        Optional<Type> typeOptional = typeService.findById(id);
            ModelAndView modelAndView = new ModelAndView("/type/update");
            modelAndView.addObject("type", typeOptional.get());
            return modelAndView;
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("type") Type type,
                         RedirectAttributes redirect) {
        typeService.save(type);
        redirect.addFlashAttribute("message", "Update type successfully");
        return "redirect:/types";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) throws NotFountException {
        Optional<Type> typeOptional = typeService.findById(id);
            typeService.remove(id);
            return new ModelAndView("redirect:/types");
    }

    @GetMapping("/count")
    public ModelAndView countProvince() {
        ModelAndView modelAndView = new ModelAndView("/type/count");
        Iterable<ITypeDTO> typeDTOS = typeService.getAllTypes();
        modelAndView.addObject("typeDTOS", typeDTOS);
        return modelAndView;
    }
    @ExceptionHandler(NotFountException.class)
    public String handleNotFound(Model model, NotFountException e) {
        return "/error_404";
    }
}
