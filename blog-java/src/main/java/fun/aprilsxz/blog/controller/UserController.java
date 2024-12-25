package fun.aprilsxz.blog.controller;

import fun.aprilsxz.blog.domain.common.Result;
import fun.aprilsxz.blog.domain.dto.LoginFormDTO;
import fun.aprilsxz.blog.domain.vo.UserLoginVO;
import fun.aprilsxz.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping()
@Api(tags = "用户相关接口")
public class UserController {
    @Resource
    UserService userService;

    @ApiOperation("用户登录接口")
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody @Validated LoginFormDTO loginFormDTO){
        UserLoginVO userLoginVO = userService.login(loginFormDTO);
        return Result.ok(userLoginVO);
    }

    @ApiOperation("用户注册接口")
    @PostMapping("/register")
    public Result<UserLoginVO> register(@RequestBody @Validated LoginFormDTO loginFormDTO){
        UserLoginVO userLoginVO = userService.register(loginFormDTO);
        return Result.ok(userLoginVO);
    }

    /*
        export  function loginByQQ(code: string) {
          return instance({
            url: "/qqLogin?code=" + code,
            method: "get",
          })
        }
     */
    @GetMapping("/qqLogin")
    @ApiOperation("qq登录")
    Result<UserLoginVO> qqLogin(@RequestParam("code") String code){
        UserLoginVO userLoginVO = userService.qqLogin(code);
        return Result.ok(userLoginVO);
    }
}
