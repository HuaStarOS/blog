package com.qzh.controller.admin;

import com.qzh.pojo.Type;
import com.qzh.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;

/**
 * @Author: qzh
 * @Date: 2020/12/25 15:21
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String list(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                                        Pageable pageable, Model model){

        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    /**
     * 跳转到types_input页面(进行新增分类操作)
     * @param model
     * @return
     */
    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types_input";
    }

    /**
     * 跳转到types_input页面(进行修改分类操作)
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model) {
        model.addAttribute("type",typeService.getType(id));
        return "admin/types_input";
    }

    /**
     * 调用typeService添加新的分类
     * @param type
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result,RedirectAttributes attributes){
        Type typeByName = typeService.getTypeByName(type.getName());

        if (typeByName != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/types_input";
        }
        Type t = typeService.saveType(type);
        if (t == null) {
            //为空,说明添加失败提示用户
            attributes.addFlashAttribute("message","新增失败");
        }else {
            //不为空,说明添加成功提示用户
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/types";
    }

    /**
     * 调用typeService修改分类
     * @param type
     * @param result
     * @param id
     * @param attributes
     * @return
     */
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result,@PathVariable("id") Long id,RedirectAttributes attributes){

        Type typeByName = typeService.getTypeByName(type.getName());

        if (typeByName != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/types_input";
        }
        Type t = typeService.updateType(id,type);
        if (t == null) {
            //为空,说明添加失败提示用户
            attributes.addFlashAttribute("message","更新失败");
        }else {
            //不为空,说明添加成功提示用户
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes attributes){

        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }
}
