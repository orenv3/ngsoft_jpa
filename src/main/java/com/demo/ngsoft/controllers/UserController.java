package com.demo.ngsoft.controllers;


import com.demo.ngsoft.dao.services.UserService;
import com.demo.ngsoft.entities.User;
import com.demo.ngsoft.requestObjects.UpdateUserRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Validated
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "UserController", description = "The User API. " +
        "Contains all the operations that can be performed on User table.")
@RequestMapping("/api/userTable/")
@RestController
public class UserController {

    private final UserService userService;



    /**
     * Admin privilege
     * Delete user by ID
     * @param id User id
     * @return
     */
    @DeleteMapping("admin/deleteUser/{id}")
    public String delete(@NotNull @Min(2) @PathVariable("id") long id) {//1 is default admin
        return userService.deleteUser(id);
    }

    /**
     * Admin privilege
     * update user details
     * @param updateObj
     * @return
     */
    @PutMapping("admin/updateUser")
    public User update(@Valid @RequestBody() UpdateUserRequest updateObj) {
        return userService.updateUser(updateObj);
    }

    /**
     * Admin privilege
     * Get list of all user in DB
     * @return
     */
    @GetMapping("admin/allUserList")
    public List<User> getAllUserList(){
        List<User> userList = userService.getAllUserList();

        return userList;
    }

    /**
     * Admin privilege
     * Get list of all user in DB with PAGINATION
     * @return List<User>
     */
    @GetMapping("admin/allUserListWithPagination")
    public List<User> getAllUserListWithPagination(@NotNull int pageNumber, @NotNull int pageSize){
        return userService.getAllUserListWithPageRequest(pageNumber, pageSize);
    }


}
