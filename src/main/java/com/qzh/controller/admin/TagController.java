package com.qzh.controller.admin;

import com.qzh.pojo.Tag;
import com.qzh.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
 * @Date: 2020/12/25 20:06
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                               Pageable pageable, Model model){

        Page<Tag> tags = tagService.listTag(pageable);
        model.addAttribute("page",tags);
        return "admin/tags";
    }

    /**
     * 跳转到tags_input页面(进行新增标签操作)
     * @param model
     * @return
     */
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags_input";
    }

    /**
     * 调用tagService添加新的标签
     * @param tag
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        Tag tagByName = tagService.getTagByName(tag.getName());

        if (tagByName != null) {
            result.rejectValue("name","nameError","不能添加重复的标签");
        }
        if (result.hasErrors()) {
            return  "admin/tags_input";
        }
        Tag t = tagService.saveTag(tag);
        if (t == null) {
            //为空,说明添加失败提示用户
            attributes.addFlashAttribute("message","添加失败");
        } else {
            //不为空,说明添加成功提示用户
            attributes.addFlashAttribute("message","添加成功");
        }

        return "redirect:/admin/tags";
    }

    /**
     * 跳转到tags_input页面(进行修改标签操作)
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model){
        Tag tag = tagService.getTag(id);
        model.addAttribute("tag",tag);
        return "admin/tags_input";
    }

    /**
     * 调用tagService修改新的标签
     * @param tag
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult result,@PathVariable("id") Long id, RedirectAttributes attributes){

        Tag tagByName = tagService.getTagByName(tag.getName());

        if (tagByName != null) {
            result.rejectValue("name","nameError","不能添加重复的标签");
        }
        if (result.hasErrors()) {
            return  "admin/tags_input";
        }
        Tag t = tagService.updateTag(id,tag);
        if (t == null) {
            //为空,说明修改失败提示用户
            attributes.addFlashAttribute("message","修改失败");
        } else {
            //不为空,说明修改成功提示用户
            attributes.addFlashAttribute("message","修改成功");
        }

        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }

}
