package com.tsco.web.controller;

import com.tsco.api.domain.UserDTO;
import com.tsco.web.domain.vo.RegisterForm;
import com.tsco.web.domain.Response;
import com.tsco.web.exception.ExceptionCode;
import com.tsco.web.exception.WebException;
import com.tsco.web.service.impl.UserService;
import com.tsco.web.utils.PatternRegex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public Response register(RegisterForm registerForm) {
        if (registerForm.getEmail() == null || registerForm.getPassword() == null) {
            throw new WebException(ExceptionCode.INVALID_PARAMETER, "邮箱或者密码不能为空");
        }
        //校验email
        if (registerForm.getEmail().matches(PatternRegex.email)) {
            throw new WebException(ExceptionCode.INVALID_PARAMETER, "邮箱格式不正确");
        }
        Optional<UserDTO> optionalUserDTO = userService.creatUser(userService.generateUserDTO(registerForm));
        if (optionalUserDTO.isPresent()) {
            return Response.SUCCESS();
        }
        return Response.FAIL(ExceptionCode.INNER_ERROR, "创建用户异常");
    }
}
