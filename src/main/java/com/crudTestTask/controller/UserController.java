package com.crudTestTask.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crudTestTask.service.UserService;
import com.crudTestTask.user.User;

@Controller
public class UserController {
    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

	@RequestMapping(value = "users", method = RequestMethod.GET)
	public String listUser(@RequestParam(value = "page", required = false) Integer pageNum, Model model) {
		List<User> users = userService.listUser();
		PagedListHolder<User> pagedListHolder = new PagedListHolder<User>(users);
		pagedListHolder.setPageSize(5);
		int page = (pageNum != null) ? pageNum : 0;
		model.addAttribute("maxPages", pagedListHolder.getPageCount());
		if (page < 1 || page > pagedListHolder.getPageCount()) {
			page = 1;
		}
		model.addAttribute("page", page);
		if (page > pagedListHolder.getPageCount()) {
			pagedListHolder.setPage(0);
		} else {
			pagedListHolder.setPage(page - 1);
		}
		model.addAttribute("user", new User());
		model.addAttribute("listUser", pagedListHolder.getPageList());
		return "users";
	}

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user){
        if (user.getId() == 0){
            this.userService.addUser(user);
        } else{
            this.userService.updateUser(user);
        }
        return "redirect:/users";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id) {
        this.userService.removeUser(id);
        return "redirect:/users";
    }

    @RequestMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
    	User user = userService.getUserById(id);
        model.addAttribute("user", user);
        List<User> users = new ArrayList<User>();
        users.add(user);
        model.addAttribute("listUser", users);
        return "users";
    }

    @RequestMapping("userdata/{id}")
    public String userData(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", this.userService.getUserById(id));
        return "userdata";
    }

    @RequestMapping(value = "find", method = RequestMethod.POST)
    public String findUserByName(@ModelAttribute("user") User user, Model model){
        model.addAttribute("listUser", this.userService.listUserBy(user));
        return "find";
    }

}
